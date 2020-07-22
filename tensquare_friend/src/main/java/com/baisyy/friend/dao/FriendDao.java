package com.baisyy.friend.dao;

import com.baisyy.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author chenlin
 * @create 2020/6/28/14:00
 */
public interface FriendDao extends JpaRepository<Friend,String>, JpaSpecificationExecutor<Friend> {

    public Friend findByUseridAndFriendid(String userid,String friendid);

    @Modifying
    @Query(value = "UPDATE tb_friend SET islike=? where userid=? AND friendid=?",nativeQuery = true)
    public void updateIsLike(String islike,String userid,String friendid);

    @Modifying
    @Query(value = "delete from tb_friend where  userid = ? and friendid = ?",nativeQuery = true)
    void deletefriend(String userId, String friendid);
}
