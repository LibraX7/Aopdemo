package com.example.aopdemo.controller;

import com.example.aopdemo.annotation.AutoFill;
import com.example.aopdemo.entities.UserInfo;
import com.example.aopdemo.service.Aopservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aop")
public class AopController {
    @Autowired
    private Aopservice aopservice;
    @PostMapping("/insert")
    public String insert (@RequestBody UserInfo userInfo){
        aopservice.insert(userInfo);
        return "ok";
    }
    @GetMapping("/hello")
    @AutoFill("hello")
    public String hello(@RequestParam String name){
        System.out.println("调用中"+ name);
        return "hello" + name;
    }
}
