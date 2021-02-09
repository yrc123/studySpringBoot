package com.studyspringboot.ch4.AOP.service;


import com.studyspringboot.ch4.AOP.User;
import com.studyspringboot.ch4.AOP.service.UserServer;
import org.springframework.stereotype.Service;

@Service
public class UserServerImpl implements UserServer {
	@Override
	public void printUser(User user) {
		if(user.getId()==null)
			throw new RuntimeException("用户参数为空");
		System.out.println("id = "+user.getId());
		System.out.println("username = "+user.getUsername());
		System.out.println("password = "+user.getPassword());
	}
}
