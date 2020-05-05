package com.tensquare.spit.controller;

import com.baissy.tensquare.entity.Result;
import com.baissy.tensquare.entity.StatusCode;
import com.tensquare.spit.poji.Spit;
import com.tensquare.spit.serice.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenlin
 * @create 2020/5/5/16:03
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String spitId){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(spitId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(Spit spit){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"保存成功");
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.PUT)
    public Result update(@PathVariable String spitId,@RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true,StatusCode.OK,"删除成功");
    }


}
