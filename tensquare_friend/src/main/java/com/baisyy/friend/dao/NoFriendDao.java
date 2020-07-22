package com.baisyy.friend.dao;

import com.baisyy.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author chenlin
 * @create 2020/6/28/15:15
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String>, JpaSpecificationExecutor<NoFriend> {
    NoFriend findByUseridAndFriendid(String userId, String friendId);
}
