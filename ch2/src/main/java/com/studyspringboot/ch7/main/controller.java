package com.studyspringboot.ch7.main;

import com.studyspringboot.ch6.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class controller {
	@Autowired
	private RedisTemplate redisTemplate;
	@RequestMapping("/getvalue")
	@ResponseBody
	public String getValue(String key){
		String res= redisTemplate.opsForValue().get(key).toString();
		return res;
	}
	@RequestMapping("/setvalue")
	@ResponseBody
	public void setValue(String key,String value){
		redisTemplate.opsForValue().set(key,new User());
	}
	@RequestMapping("/setuser")
	@ResponseBody
	public void setUser(String key){
		redisTemplate.opsForValue().set(key,new User());
	}
	@RequestMapping("/getuser")
	@ResponseBody
	public User getUser(String key){
		return (User) redisTemplate.opsForValue().get(key);
	}
	@RequestMapping("/getmultiuser")
	@ResponseBody
	public Object getMultiUser(String key){
		return redisTemplate.execute(new SessionCallback() {
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				operations.watch(key);
				operations.multi();
				operations.opsForValue().set(key,"1");
				operations.opsForValue().get(key);
				return operations.exec();
			}
		});
	}
}
