package com.studyspringboot.ch6;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping("/hello")
	@ResponseBody
	public String hello(Long id){
		return userService.hello();
	}

	@RequestMapping("/get")
	@ResponseBody
	public User getUser(Long id){
		return userService.getUser(id);
	}

//	@RequestMapping("/insert")
//	@ResponseBody
//	public Map<String,Object> insertUser(String userName, String note){
//		User user = new User();
//		user.setUserName(userName);
//		user.setNote(note);
//		int num = userService.insertUser(user);
//		Map<String, Object> map = new HashMap<>();
//		map.put("insert",num);
//		map.put("user",user);
//		return map;
//	}

	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insertUser(User user){
		int num = userService.insertUser(user);
		Map<String, Object> map = new HashMap<>();
		map.put("insert",num);
		map.put("user",user);
		return map;
	}
}
