package com.baisyy.friend.service;

import com.baisyy.friend.dao.FriendDao;
import com.baisyy.friend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author chenlin
 * @create 2020/6/28/13:49
 */
@Service
@Transactional
public class FriendService {
    @Autowired
    FriendDao friendDao;
    public int addFriend(String userId, String friendId) {
        Friend friend = friendDao.findByUseridAndFriendid(userId, friendId);
        if(friend!=null){
            return 0;
        }
        friend=new Friend();
        friend.setUserid(userId);
        friend.setFriendid(friendId);
        friend.setIslike("0");
        friendDao.save(friend);
        if(friendDao.findByUseridAndFriendid(friendId, userId)!=null){
            friendDao.updateIsLike("1",userId,friendId);
            friendDao.updateIsLike("1",friendId,userId);
        }
        return 1;
    }

    public void deleteFriend(String userId, String friendid) {
        friendDao.deletefriend(userId,friendid);
        friendDao.updateIsLike("0",userId,friendid);
    }
}
