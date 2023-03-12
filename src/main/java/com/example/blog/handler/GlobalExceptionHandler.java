package com.example.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

//@ControllerAdvice 란?
//@Controller나 @RestController에서 발생한 예외를 한 곳에서 관리하고 처리할 수 있게 도와주는 어노테이션이다.
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    
    //여기에 설정한 EXception을 받는다. 최상위 Exception으로 설정하면 모든 Exception을 받을 수 있다.
    @ExceptionHandler(value=Exception.class)
    public String exceptionHandler(Exception e){
        return "<h1>"+ e.getMessage() + "</h1>";
    }
}
