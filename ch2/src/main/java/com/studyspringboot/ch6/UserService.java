package com.studyspringboot.ch6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	public String hello(){
		return "hello";
	}
	public User getUser(Long id){
		return userDao.getUser(id);
	}
	public int insertUser(User user){
		return userDao.insertUser(user);
	}
}
