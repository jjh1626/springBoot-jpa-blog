package com.example.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
    
    @GetMapping("/httpTest")
    public String getTest(Member m) {
        return "get 요청 :" + m.getId() + ", " + m.getUsername() + ", " + m.getPasswd() + ", " + m.getEmail();
    }

    @PostMapping("/httpTest")
    public String postTest(Member m) {
        return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPasswd() + ", " + m.getEmail();
    }

    @PostMapping("/httpBody")
    public String postBodyTest(@RequestBody Member m) {
        return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPasswd() + ", " + m.getEmail();
    }

    @PutMapping("/httpTest")
    public String putTest(@RequestBody Member m) {
        return "put 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPasswd() + ", " + m.getEmail();
    }

    @DeleteMapping("/httpTest")
    public String deleteTest() {
        return "delete 요청";
    }
    
}
