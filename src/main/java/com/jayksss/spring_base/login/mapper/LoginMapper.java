package com.jayksss.spring_base.login.mapper;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface LoginMapper {

    Map<String, Object> selectLoginUser(Map<String, Object> params);
}
