package com.baissy.search.dao;

import com.baissy.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author chenlin
 * @create 2020/5/25/13:56
 */
@Mapping
public interface ArticleDao extends ElasticsearchRepository<Article,String > {

    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
    //org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'articleController': Unsatisfied dependency expressed through field 'articleService'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'articleService': Unsatisfied dependency expressed through field 'articleDao'; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'articleDao': Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: Paging query needs to have a Pageable parameter!
    // Offending method public abstract org.springframework.data.domain.Page com.baissy.search.dao.ArticleDao.findByTitleOrContentLike(java.lang.String,java.lang.String,org.springframework.data.domain.PageRequest)
}
