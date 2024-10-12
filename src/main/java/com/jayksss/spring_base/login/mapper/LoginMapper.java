package com.jayksss.spring_base.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LoginMapper {

    Map<String, Object> selectLoginUser(Map<String, Object> params);
}
