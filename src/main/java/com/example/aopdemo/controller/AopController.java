package com.example.aopdemo.controller;

import com.example.aopdemo.entities.UserInfo;
import com.example.aopdemo.service.Aopservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
