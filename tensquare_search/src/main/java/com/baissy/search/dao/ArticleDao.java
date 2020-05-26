package com.baissy.search.dao;

import com.baissy.search.pojo.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author chenlin
 * @create 2020/5/25/13:56
 */
public interface ArticleDao extends ElasticsearchRepository<Article,String > {
}
