package com.baissy.qa.client;

import com.baissy.qa.client.impl.BaseClientImpl;
import com.baissy.tensquare.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author chenlin
 * @create 2020/6/20/20:45
 */
//@RestController
@FeignClient(value = "tensquare-base",fallback = BaseClientImpl.class)
public interface BaseClient {
    @GetMapping(value = "/label/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId);
}
