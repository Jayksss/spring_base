package com.jayksss.spring_base.config;

import com.jayksss.spring_base.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginService loginService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrfConfigurer -> csrfConfigurer.disable()) // CSRF 비활성화 (필요 시 활성화)
                .authorizeHttpRequests(authorize -> authorize
                        // 공용 페이지 및 리소스에 대해 예외 처리
                        .requestMatchers("/about", "/contact", "/login", "/signup").permitAll() // 공용 페이지
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll() // 정적 리소스
                        .requestMatchers("/error", "/favicon.ico").permitAll() // 오류 페이지 및 파비콘
                        // 특정 권한이 필요한 요청 설정
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        // 나머지 요청은 인증이 필요
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")  // 로그인 처리 URL
                        .permitAll()
                        .defaultSuccessUrl("/index", true)
                        .failureHandler((request, response, exception) -> {
                            // 로그인 실패 원인에 따라 메시지 설정
                            String errorMessage = "로그인에 실패했습니다. 사용자 이름이나 비밀번호를 다시 확인하세요.";
                            if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
                                errorMessage = "계정이 비활성화되었습니다.";
                            } else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
                                errorMessage = "계정이 만료되었습니다.";
                            }
                            // 로그인 페이지로 리다이렉트하면서 에러 메시지를 전달
                            request.getSession().setAttribute("errorMessage", errorMessage);
                            response.sendRedirect("/login?error=true");
                        })
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 처리 URL
                        .logoutSuccessUrl("/login?logout=true") // 로그아웃 성공 후 이동할 URL
                        .invalidateHttpSession(true) // 세션 무효화
                        .deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.sendRedirect("/login");
                        })
                );

        return http.build();
    }
}