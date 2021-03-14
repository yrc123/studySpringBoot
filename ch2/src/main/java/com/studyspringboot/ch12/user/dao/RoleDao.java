package com.studyspringboot.ch12.user.dao;

import com.studyspringboot.ch12.user.pojo.Role;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
	public List<Role> findRolesByUserName(@NotNull String userName);
}
