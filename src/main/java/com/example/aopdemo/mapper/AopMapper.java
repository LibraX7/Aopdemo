package com.example.aopdemo.mapper;

import com.alibaba.druid.sql.visitor.functions.Insert;
import com.example.aopdemo.annotation.AutoFill;
import com.example.aopdemo.entities.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface AopMapper {
    @AutoFill("Insert")
    public void insert(UserInfo userInfo);

}
