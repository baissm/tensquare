package com.baisyy.friend.service;

import com.baisyy.friend.dao.NoFriendDao;
import com.baisyy.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author chenlin
 * @create 2020/6/28/15:15
 */
@Service
@Transactional
public class NoFriendService {
    @Autowired
    NoFriendDao noFriendDao;
    public int addNoFriend(String userId, String friendId) {
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userId, friendId);
        if(noFriend != null){
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendId);
        noFriendDao.save(noFriend);
        return 1;
    }
}
