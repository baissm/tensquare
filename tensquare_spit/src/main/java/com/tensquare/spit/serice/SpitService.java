package com.tensquare.spit.serice;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.poji.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenlin
 * @create 2020/5/5/15:58
 */
@Service
@Transactional

public class SpitService {
    @Autowired
    private SpitDao spitDao;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private util.IdWorker idWorker;

    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    public void save(Spit spit) {
        spit.set_id(idWorker.nextId()+"");
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        //如果有父节点，父节点回复数要加1
        if(spit.getParentid()!=null&&!"".equals(spit.getParentid())){
            Query query=new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update=new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    public void update(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spitDao.save(spit);
    }

    public void deleteById(String id){
        spitDao.deleteById(id);
    }

    public Page<Spit> findByParentId(String parentid,int page,int size){
        Pageable pageable= PageRequest.of(page-1, size);
        return spitDao.findByParentid(parentid,pageable);
    }

    public void thumbup(String spitId) {
//        Spit spit=spitDao.findById(spitId).get();
//        spit.setThumbup((spit.getThumbup()==null?0:spit.getThumbup())+1);
//        spitDao.save(spit);

        //自增
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update=new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }

}
