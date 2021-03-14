package com.studyspringboot.ch12.user.dao;

import com.studyspringboot.ch12.user.pojo.MyUser;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;


@Repository
public interface UserDao {
	public MyUser findUserByName(@NotNull String userName);
}
