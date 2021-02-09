package com.studyspringboot.ch7.mycache.service;

import com.studyspringboot.ch6.UserDao;
import com.studyspringboot.ch7.mycache.dao.MyDao;
import com.studyspringboot.ch7.mycache.pojo.User;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyServiceImpl implements MyService {
	@Autowired
	private MyDao myDao= null;

	@Override
	@Transactional
	@CachePut(value="redisCache",key = "'redis_user_'+#result.id")
	public User insertUser(User user) {
		myDao.insertUser(user);
		return user;
	}

	@Override
	@Transactional
	@Cacheable(value = "redisCache" ,key = "'redis_user_'+#id")
	public User getUser(Long id) {
		return myDao.getUser(id);
	}

	@Override
	@Transactional
	@CachePut(value = "redisCache" ,key = "'redis_user_'+#id",
		condition = "#result != 'null'")
	public User updateUserName(Long id, String userName) {
		User user = this.getUser(id);
		if(user == null){
			return null;
		}
		user.setUserName(userName);
		myDao.updateUser(user);
		return user;
	}

	@Override
	public List<User> findUsers(String userName, String note) {
		return myDao.findUsers(userName,note);
	}

	@Override
	@Transactional
	@CacheEvict(value = "redisCache" ,key = "'redis_user_'+#id",beforeInvocation = false)
	public int deleteUser(Long id) {
		return myDao.deleteUser(id);
	}

}
