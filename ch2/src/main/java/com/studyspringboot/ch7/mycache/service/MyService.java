package com.studyspringboot.ch7.mycache.service;

import com.studyspringboot.ch7.mycache.pojo.User;

import java.util.List;

public interface MyService {
	public User insertUser(User user);
	public User getUser(Long id);
	public User updateUserName(Long id, String userName);
	public List<User> findUsers(String userName, String note);
	public int deleteUser(Long id);
}
