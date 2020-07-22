package com.baissy.qa.client.impl;

import com.baissy.qa.client.BaseClient;
import com.baissy.tensquare.entity.Result;
import com.baissy.tensquare.entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @author chenlin
 * @create 2020/6/30/13:44
 */
@Component
public class BaseClientImpl implements BaseClient {
    @Override
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR,"触发了熔断器");
    }
}
