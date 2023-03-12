package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    
    //http://localhost:8080/
    @GetMapping("/")
    public String index() {
        // /WEB-INF/views/index
        return "default";
    }
}