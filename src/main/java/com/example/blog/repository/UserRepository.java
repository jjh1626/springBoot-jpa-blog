package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.model.User;

// DAO
// 자동으로 Bean 으로 등록된다.
// @Repository 어노테이션 생략 가능
public interface UserRepository extends JpaRepository<User,Integer> {
    
}
