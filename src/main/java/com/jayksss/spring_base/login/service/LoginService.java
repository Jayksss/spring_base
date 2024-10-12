package com.jayksss.spring_base.login.service;

import java.util.Map;
import java.util.Optional;

public interface LoginService {

    Optional<Map<String, Object>> selectLoginUser(Map<String, Object> params);
}
