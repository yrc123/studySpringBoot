package com.studyspringboot.ch7.mycache.dao;

import com.studyspringboot.ch7.mycache.pojo.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyDao {
	//获取单个用户
	User getUser(Long id);
	//插入用户
	int insertUser(User user);
	//更新用户
	int updateUser(User user);
	//查询用户
	List<User> findUsers(@Param("userName") String userName, @Param("note") String note);
	//删除用户
	int deleteUser(Long id);
}
