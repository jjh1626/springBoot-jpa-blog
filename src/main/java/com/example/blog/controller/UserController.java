package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    
    //회원가입 폼
    @GetMapping("/user/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }
    
    //로그인 폼
    @GetMapping("/user/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }
}
