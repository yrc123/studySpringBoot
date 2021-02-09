package com.studyspringboot.ch7.mycache.controller;

import com.studyspringboot.ch7.mycache.pojo.User;
import com.studyspringboot.ch7.mycache.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {
	@Autowired
	private MyService myService = null;

	@RequestMapping("/getUser")
	@ResponseBody
	public User getUser(Long id){
		return myService.getUser(id);
	}
	@RequestMapping("/insertUser")
	@ResponseBody
	public User insertUser(String userName, String note){
		User user = new User();
		user.setUserName(userName);
		user.setNote(note);
		myService.insertUser(user);
		return user;
	}

	@RequestMapping("/findUsers")
	@ResponseBody
	public List<User> findUsers(String userName, String note){
		return myService.findUsers(userName,note);
	}

	@RequestMapping("/updateUserName")
	@ResponseBody
	public Map<String,Object> updateUserName(Long id, String userName){
		User user = myService.updateUserName(id,userName);
		boolean flag = user != null;
		String message = flag?"update success":"update fail";
		return resultMap(flag,message);
	}

	@RequestMapping("/deleteUser")
	@ResponseBody
	public Map<String, Object> deleteUser(Long id){
		int result = myService.deleteUser(id);
		boolean flag = result==1;
		String message = flag? "del success":"del fail";
		return resultMap(flag,message);
	}

	private Map<String,Object> resultMap(boolean success, String message){
		Map<String, Object> map = new HashMap<>();
		map.put("flag",success);
		map.put("message",message);
		return map;
	}
}
