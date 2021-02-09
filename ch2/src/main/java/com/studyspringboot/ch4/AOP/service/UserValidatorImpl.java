package com.studyspringboot.ch4.AOP.service;

import com.studyspringboot.ch4.AOP.User;

//引入的新类
public class UserValidatorImpl implements UserValidator{
	//判断是否有userId
	@Override
	public boolean validate(User user) {
		System.out.println("新接口");
		return user.getId()!=null;
	}
}
