package com.studyspringboot.ch5.dao;

import com.studyspringboot.ch5.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBatisUserDao {
	public User getUser(Long id);
}
