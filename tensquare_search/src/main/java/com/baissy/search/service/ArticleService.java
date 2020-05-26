package com.baissy.search.service;

import com.baissy.search.dao.ArticleDao;
import com.baissy.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenlin
 * @create 2020/5/26/8:50
 */
@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
//    @Autowired
//    util.IdWorker idWorker;
    public void save(Article article){
//        article.setId(idWorker.nextId()+"");
        articleDao.save(article);

    }
}
