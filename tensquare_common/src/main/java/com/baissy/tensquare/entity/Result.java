package com.baissy.tensquare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author chenlin
 * @create 2020/4/30/23:43
 */
@Data
@AllArgsConstructor
public class Result {
    private  Boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public Result(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
}
