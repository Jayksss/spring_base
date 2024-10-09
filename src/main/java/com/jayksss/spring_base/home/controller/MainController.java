package com.jayksss.spring_base.home.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "login/login"; // /templates/login/login.html 뷰 반환
    }

    @GetMapping("/main")
    public String main() {
        return "main/main"; // /templates/main/main.html 뷰 반환
    }

    @GetMapping("/index")
    public String index() {
        return "main/index"; // /templates/main/index.html 뷰 반환
    }
}