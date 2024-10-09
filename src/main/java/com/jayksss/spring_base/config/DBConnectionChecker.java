package com.jayksss.spring_base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBConnectionChecker implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        checkDatabaseConnection();
    }

    public void checkDatabaseConnection() {
        try {
            jdbcTemplate.execute("SELECT 1"); // 간단한 쿼리 실행
            System.out.println("■ DB 연결 성공 확인!");
        } catch (Exception e) {
            System.err.println("■ DB 연결 실패: " + e.getMessage());
        }
    }
}