package com.example.aopdemo.service.impl;

import com.example.aopdemo.entities.UserInfo;
import com.example.aopdemo.mapper.AopMapper;
import com.example.aopdemo.service.Aopservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Aopserviceimpl implements Aopservice {
    @Autowired
    private AopMapper aopMapper;
    @Override
    public void insert(UserInfo userInfo) {
        aopMapper.insert(userInfo);
    }
}
