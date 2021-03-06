package com.baissy.user.controller;

import com.baissy.tensquare.entity.PageResult;
import com.baissy.tensquare.entity.Result;
import com.baissy.tensquare.entity.StatusCode;
import com.baissy.tensquare.entity.until.JwtUtil;
import com.baissy.user.pojo.User;
import com.baissy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
    private RedisTemplate redisTemplate;
	@Autowired
    private util.IdWorker idWorker;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;

    //更新粉丝数和关注数
    @PutMapping(value = "/{userid}/{friendid}/{x}")
    public void updateFanscountAndFollowcount(@PathVariable String userid,@PathVariable String friendid,@PathVariable int x){
        userService.updateFanscountAndFollowcount(x,userid,friendid);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody User user){
        User userLogin = userService.login(user);
        if(userLogin==null){
            return  new Result(false,StatusCode.LOGINERROR,"登陆失败");
        }
        //这里需要使前后端可以通话，采用JWT来实现。
        String token = jwtUtil.createJWT(userLogin.getId(), userLogin.getMobile(), "user");
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("role","user");
        return  new Result(true,StatusCode.OK,"登陆成功",map);
    }


	@RequestMapping(value = "/sendsms/{mobile}",method = RequestMethod.POST)
	public Result senSms(@PathVariable String mobile){
	    userService.sendSms(mobile);
	    return new Result(true,StatusCode.OK,"发送成功");
    }

    @RequestMapping(value = "/register/{code}",method = RequestMethod.POST)
    public Result regist(@PathVariable String code,@RequestBody User user){
	    //获取缓存中的验证码
        String checkcodeRedis = (String) redisTemplate.opsForValue().get("checkcode_" + user.getMobile());
        if(checkcodeRedis.isEmpty()){
            return new Result(false,StatusCode.ERROR,"请先获取手机验证码");
        }
        else {
            if(!checkcodeRedis.equals(code)){
                return new Result(false,StatusCode.ERROR,"验证码错误");
            }
            else {
                user.setId( idWorker.nextId()+"" );
                user.setFollowcount(0);//关注数
                user.setFanscount(0);//粉丝数
                user.setOnline(0L);//在线时长
                user.setRegdate(new Date());//注册日期
                user.setUpdatedate(new Date());//更新日期
                user.setLastdate(new Date());//最后登陆日期
                userService.add(user);
                return new Result(true,StatusCode.OK,"注册成功");
            }
        }
    }
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user  ){
		userService.add(user);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){

		userService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
