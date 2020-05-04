package com.baissy.tensquare.base.dao;

import com.baissy.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author chenlin
 * @create 2020/5/1/1:54
 */
//使用jpa继承需要JpaRepository、 排序分页查询JpaSpecificationExecutor
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {
}
