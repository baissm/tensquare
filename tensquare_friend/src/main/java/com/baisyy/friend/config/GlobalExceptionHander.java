package com.baisyy.friend.config;

import com.baissy.tensquare.entity.Result;
import com.baissy.tensquare.entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author chenlin
 * @create 2020/6/5/15:08
 */
@RestControllerAdvice
public class GlobalExceptionHander {
    @ExceptionHandler(RuntimeException.class)
    public Result customException(Exception e) {
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }
}
