package com.baisyy.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author chenlin
 * @create 2020/6/28/14:01
 */
@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)
public class Feiend {
}
