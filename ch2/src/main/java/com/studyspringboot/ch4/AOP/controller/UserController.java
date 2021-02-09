package com.studyspringboot.ch4.AOP.controller;

import com.studyspringboot.ch4.AOP.User;
import com.studyspringboot.ch4.AOP.service.UserServer;
import com.studyspringboot.ch4.AOP.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServer userServer = null;

	@RequestMapping("/print")
	@ResponseBody
	public User printUser(Long id, String userName, String password){
		System.out.println(userServer.getClass());
		User user = new User();
		user.setId(id);
		user.setUsername(userName);
		user.setPassword(password);
		//使用引入的验证器
		UserValidator uv = (UserValidator) this.userServer;
		if(uv.validate(user)){
			this.userServer.printUser(user);
		}else{
			System.out.println("空id");
		}
		return user;

	}

}
