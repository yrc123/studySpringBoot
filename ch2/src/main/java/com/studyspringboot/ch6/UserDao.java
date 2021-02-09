package com.studyspringboot.ch6;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	public User getUser(@Param("id") Long id);
	public int insertUser(@Param("user")User user);
}
