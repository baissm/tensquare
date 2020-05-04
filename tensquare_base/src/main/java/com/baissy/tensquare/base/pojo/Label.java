package com.baissy.tensquare.base.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author chenlin
 * @create 2020/5/1/1:46
 */
@Entity
@Table(name = "tb_label")
@Getter
@Setter
public class Label implements Serializable {
    @Id
    private String id;//id
    private String labelname;//标签名称
    private String state;//状态
    private Long count;//使用数量
    private Long fans;//关注数
    private String recommend;//是否推荐

}
