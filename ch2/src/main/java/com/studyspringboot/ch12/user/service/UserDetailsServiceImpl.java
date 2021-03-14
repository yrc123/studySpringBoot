package com.studyspringboot.ch12.user.service;

import com.studyspringboot.ch12.user.pojo.Role;
import com.studyspringboot.ch12.user.pojo.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRoleService userRoleService;
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		MyUser user = userRoleService.findUserByName(s);
		List<Role> roles = userRoleService.findRolesByUserName(s);
		UserDetails userDetails = changeToUserDetails(user, roles);
		return userDetails;
	}
	private UserDetails changeToUserDetails(MyUser user, List<Role> roles){
		List<GrantedAuthority> auths = new ArrayList<>();
		for(Role role : roles){
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
			auths.add(grantedAuthority);
		}
		UserDetails userDetails = new User(user.getUserName(),user.getPwd(),auths);
		return userDetails;
	}
}
