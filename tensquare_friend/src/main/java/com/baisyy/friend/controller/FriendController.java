package com.baisyy.friend.controller;

import com.baissy.tensquare.entity.Result;
import com.baissy.tensquare.entity.StatusCode;
import com.baisyy.friend.client.UserClient;
import com.baisyy.friend.service.FriendService;
import com.baisyy.friend.service.NoFriendService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenlin
 * @create 2020/6/28/11:50
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    FriendService friendService;
    @Autowired
    NoFriendService noFriendService;
    @Autowired
    UserClient userClient;

    //添加好友或者添加非好友
    @PutMapping(value = "/like/{friendId}/{type}")
    public Result addFriend(@PathVariable String friendId,@PathVariable String type){
        Claims claims = (Claims)request.getAttribute("claims_user");
        if(claims==null){
            return new Result(false,StatusCode.ERROR,"权限不足");
        }
        String userId=claims.getId();
        if(type!=null){
            if(type.equals("1")){
                //添加好友
                int flag = friendService.addFriend(userId,friendId);
                if(flag==0){
                    return new Result(false,StatusCode.ERROR,"不能重复添加好友");
                }
                else if(flag==1){
                    userClient.updateFanscountAndFollowcount(userId,friendId,1);
                    return new Result(true,StatusCode.OK,"添加成功");
                }
            }
            else if(type.equals("2")){
                //添加非好友
                int flag = noFriendService.addNoFriend(userId,friendId);
                if(flag==0){
                    return new Result(false,StatusCode.ERROR,"不能重复添加非好友");
                }
                else if(flag==1){
                    return new Result(true,StatusCode.OK,"添加成功");
                }
            }
            else
                return new Result(false, StatusCode.ERROR,"参数异常");
        }
        return new Result(false, StatusCode.ERROR,"参数异常");
    }

    @DeleteMapping(value="/{friendid}")
    public Result deleteFriend(@PathVariable String friendid){
        Claims claims = (Claims)request.getAttribute("claims_user");
        if(claims==null){
            return new Result(false,StatusCode.ERROR,"权限不足");
        }
        String userId=claims.getId();
        friendService.deleteFriend(userId,friendid);
        userClient.updateFanscountAndFollowcount(userId,friendid,-1);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
