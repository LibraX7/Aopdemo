package com.example.aopdemo.mapper;

import com.example.aopdemo.entities.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AopMapper {
    public void insert(UserInfo userInfo);

}
