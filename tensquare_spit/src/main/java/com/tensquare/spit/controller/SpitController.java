package com.tensquare.spit.controller;

import com.tensquare.spit.Service.SpitService;
import com.tensquare.spit.pojo.Spit;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 吴亚斌
 * create : 2019-02-22 13:58
 * description
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findAll());
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String spitId){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(spitId));
    }
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"保存成功");
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit,@PathVariable String spitId){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"保存成功");
    }
    @RequestMapping(value = "/{spitId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "/comment/{parentid}/{page}/{size}",method = RequestMethod.GET)
    public Result findByParenid(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        Page<Spit> pageDate =spitService.findByParentid(parentid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(pageDate.getTotalElements(),pageDate.getContent()));
    }

    @RequestMapping(value = "/thumbup/{spitId}",method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitId){

        //判断当前用户是否已经点在，但是i安在没有做认证，暂时先把userid写死
        String userid = "11";
        if (redisTemplate.opsForValue().get("thumbup_"+userid)!=null){
            return new Result(false,StatusCode.ERROR,"不能重复点赞");
        }

        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_"+userid,1);
        return new Result(true,StatusCode.OK,"点赞成功");
    }

}
