package com.studyspringboot.ch12.user.service;

import com.studyspringboot.ch12.user.dao.RoleDao;
import com.studyspringboot.ch12.user.dao.UserDao;
import com.studyspringboot.ch12.user.pojo.Role;
import com.studyspringboot.ch12.user.pojo.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	@Override
	@Cacheable(value="redisCache",key = "'t_user_'+#userName")
	public MyUser findUserByName(String userName){
		return userDao.findUserByName(userName);
	}

	@Override
	@Cacheable(value="redisCache",key = "'t_roles_'+#userName")
	public List<Role> findRolesByUserName(String userName) {
		return roleDao.findRolesByUserName(userName);
	}
}
