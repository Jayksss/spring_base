package com.jayksss.spring_base.login.service;

import java.util.Map;

public interface LoginService {

    Map<String, Object> selectLoginUser(Map<String, Object> params);
}
