package com.studyspringboot.ch12.user.service;

import com.studyspringboot.ch12.user.pojo.Role;
import com.studyspringboot.ch12.user.pojo.MyUser;

import java.util.List;

public interface UserRoleService {
	public MyUser findUserByName(String userName);
	public List<Role> findRolesByUserName(String userName);
}
