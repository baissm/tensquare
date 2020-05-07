package com.tensquare.spit.dao;

import com.tensquare.spit.poji.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author chenlin
 * @create 2020/5/5/15:57
 */
public interface SpitDao extends MongoRepository<Spit,String> {

    public Page<Spit> findByParentid(String parentid, Pageable pageable);


}
