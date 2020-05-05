package com.tensquare.spit.serice;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.poji.Spit;
import org.springframework.beans.factory.annotation.Autowired;
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
    private util.IdWorker idWorker;

    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    public void save(Spit spit){
        spitDao.save(spit);
    }

    public void update(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spitDao.save(spit);
    }

    public void deleteById(String id){
        spitDao.deleteById(id);
    }

}
