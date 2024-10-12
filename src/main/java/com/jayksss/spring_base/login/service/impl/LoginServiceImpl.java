package com.jayksss.spring_base.login.service.impl;

import com.jayksss.spring_base.login.mapper.LoginMapper;
import com.jayksss.spring_base.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Map<String, Object>> selectLoginUser(Map<String, Object> params) {
        // DB에서 사용자 정보 조회
        Map<String, Object> user = loginMapper.selectLoginUser(params);
        if (user == null) {
            return Optional.empty();
        }

        // 비밀번호 검증
        String inputPassword = (String) params.get("password");
        String storedPasswordHash = (String) user.get("PWD_HASH");

        if (!passwordEncoder.matches(inputPassword, storedPasswordHash)) {
            throw new UsernameNotFoundException("비밀번호가 일치하지 않습니다.");
        }

        return Optional.of(user);
    }
}