package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.dto.ResponseDto;
import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.service.UserService;

@RestController
public class UserApiController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        //DB insert
        user.setRole(RoleType.USER);
        int result = userService.userSave(user);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
    }
}
