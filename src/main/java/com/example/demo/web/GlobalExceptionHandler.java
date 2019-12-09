package com.example.demo.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

//全局异常处理器,当发生异常的时候处理
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map<String,Object> handlerException(Exception exception){
        Map<String,Object> map = new HashMap<>();
        map.put("errorCode",500);
        map.put("errorMsg",exception.toString());
        return map;
    }
}
