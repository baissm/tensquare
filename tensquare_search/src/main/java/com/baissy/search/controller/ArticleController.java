package com.baissy.search.controller;

import com.baissy.search.pojo.Article;
import com.baissy.search.service.ArticleService;
import com.baissy.tensquare.entity.Result;
import com.baissy.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenlin
 * @create 2020/5/26/8:54
 */
@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article){
        articleService.save(article);
        return new Result(true, StatusCode.OK,"添加成功");

    }

    @RequestMapping(value = "/{key}/{page}/{size}",method = RequestMethod.GET)
    public Result findByKey(@PathVariable String key,@PathVariable int page,@PathVariable int size){
        Page<Article> pageData = articleService.findByKey(key,page,size);
        return new Result(true,StatusCode.OK,"查询成功",pageData);
    }
}
