package com.tensquare.spit.dao;

import com.tensquare.spit.poji.Spit;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author chenlin
 * @create 2020/5/5/15:57
 */
public interface SpitDao extends MongoRepository<Spit,String> {

}
