package com.baissy.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.baissy.recruit.pojo.Enterprise;

import java.util.List;

/**
 * @author chenlin
 * @create 2020/5/1/13:28
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{
	public List<Enterprise> findByIshot(String ishot);
}
