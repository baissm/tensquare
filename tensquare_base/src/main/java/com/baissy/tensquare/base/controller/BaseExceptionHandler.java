package com.baissy.tensquare.base.controller;

import com.baissy.tensquare.entity.Result;
import com.baissy.tensquare.entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author chenlin
 * @create 2020/5/1/2:37
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }
}
