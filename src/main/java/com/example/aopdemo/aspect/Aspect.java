package com.example.aopdemo.aspect;

import com.example.aopdemo.OperationType;
import com.example.aopdemo.annotation.AutoFill;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@org.aspectj.lang.annotation.Aspect
@Component
@Slf4j
public class Aspect {
    @Pointcut("execution(* com.example.aopdemo.*.*.*(..)) && @annotation(com.example.aopdemo.annotation.AutoFill) ")
    public void pointcut(){

    }
    @Before("pointcut()")
    public void autoFill(JoinPoint joinPoint){
        //获取到当前拦截器的方法上的数据库操作类型

        MethodSignature methodSignature =(MethodSignature) joinPoint.getSignature();//方法签名对象（向下转型）
        AutoFill autoFill = methodSignature.getMethod().getAnnotation(AutoFill.class);//获得方法上的注解对象
        String s  = autoFill.value();

        //获取到当前被拦截的参数————实体类的对象
        Object[] arg = joinPoint.getArgs();
        if (arg == null || arg.length == 0){
            return;
        }
        Object objects = arg[0];//为什么是0，一般把实体类参数放在第一个

        //准备赋值的数据
        LocalDateTime now = LocalDateTime.now();

        //根据当前不同的操作类型，为对应的属性通过反射来赋值
        if(s.equals("Insert")){
            //为4个公共字段赋值
            try {

                Method setUpdateTime = objects.getClass().getDeclaredMethod("setUpdateTime",LocalDateTime.class);
                //通过反射对对象属性赋值(invoke实现实施的意思)
                setUpdateTime.invoke(objects,now);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            //
        }
    }

    @Around("pointcut()")
    public Object autoLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String classname = proceedingJoinPoint.getTarget().toString();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] array = proceedingJoinPoint.getArgs();

        ObjectMapper objectMapper = new ObjectMapper();

        log.info("调用前：" + classname + " :" + methodName + "传递的参数为:" + objectMapper.writeValueAsString(array) );

        Object obj = proceedingJoinPoint.proceed();

        log.info("调用后：" + classname + " :" + methodName + "返回值为:" + objectMapper.writeValueAsString(obj) );

        return obj;

    }

}
