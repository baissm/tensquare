package com.baisyy.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenlin
 * @create 2020/6/29/10:18
 */
@RestController
@FeignClient("tensquare-user")
public interface UserClient {
    @PutMapping(value = "/user/{userid}/{friendid}/{x}")
    public void updateFanscountAndFollowcount(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid, @PathVariable("x") int x);
}
