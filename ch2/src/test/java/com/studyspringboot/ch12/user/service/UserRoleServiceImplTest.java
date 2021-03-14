package com.studyspringboot.ch12.user.service;

import com.studyspringboot.ch12.user.pojo.Role;
import com.studyspringboot.ch12.user.pojo.MyUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = com.studyspringboot.ch12.user.main.Application.class)
@RunWith(SpringRunner.class)
class UserRoleServiceImplTest {
	@Autowired
	UserRoleService service;

	@Test
	void findUserByName() {
		MyUser user = service.findUserByName("Admin1");
		Assert.assertEquals(user.getUserName(),"Admin1");
	}

	@Test
	void findRolesByUserName() {
		List<Role> roles = service.findRolesByUserName("Admin1");
		for(Role role : roles){
			System.out.println(role.getRoleName());
			System.out.println(role.getNote());
		}
	}
}