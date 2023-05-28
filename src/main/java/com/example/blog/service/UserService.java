package com.example.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록 해줌.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public int userSave(User user) {
        int result = -1;
        try {
            userRepository.save(user);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("UserService:userSave()"+ e.getMessage());
        }
        return result;
    }
}
