package com.jayksss.spring_base.main.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/main")
    public String main() {
        return "main/main"; // /templates/main/main.html 뷰 반환
    }

    @GetMapping("/index")
    public String index() {
        return "main/index"; // /templates/main/index.html 뷰 반환
    }
}