package com.baissy.qa.dao;

import com.baissy.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    @Query(value = "select * from tb_problem,tb_pl where id=problemid and labelid=? order by relytime desc ",nativeQuery = true)
    public Page<Problem> newlist(String lableid, Pageable pageable);

    @Query(value = "select * from tb_problem,tb_pl where id=problemid and labelid=? order by reply desc ",nativeQuery = true)
    public Page<Problem> hotlist(String lableid, Pageable pageable);

    @Query(value = "select * from tb_problem,tb_pl where id=problemid and labelid=? and reply=0 order by createtime desc ",nativeQuery = true)
    public Page<Problem> waitlist(String lableid, Pageable pageable);
}
