package com.jayksss.spring_base.login.service.impl;

import com.jayksss.spring_base.login.mapper.LoginMapper;
import com.jayksss.spring_base.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;

    @Override
    public Map<String, Object> selectLoginUser(Map<String, Object> params) {
        return loginMapper.selectLoginUser(params);
    }
}