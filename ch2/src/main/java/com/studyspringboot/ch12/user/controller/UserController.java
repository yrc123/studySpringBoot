package com.studyspringboot.ch12.user.controller;

import com.studyspringboot.ch12.user.pojo.JsonLogin;
import com.studyspringboot.ch12.user.pojo.MyUser;
import com.studyspringboot.ch12.user.pojo.Role;
import com.studyspringboot.ch12.user.service.UserRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "UserController")
@Controller
public class UserController {
	@Autowired
	UserRoleService service;
	@Autowired
	private AuthenticationManager authenticationManager;
	@PostMapping("/login")
	@ResponseBody
	public Map<String, Object> login(@RequestBody @Valid JsonLogin json){
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(json.getUsername(), json.getPassword());
		Authentication authenticate = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		HashMap<String, Object> map = new HashMap<>();
		map.put("status","success");
		return map;
	}
	@PostMapping("/loginNoJson")
	@ResponseBody
	public Map<String, Object> loginNoJson(JsonLogin jsonLogin){
		System.out.println(jsonLogin.getUsername()+jsonLogin.getPassword());
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(jsonLogin.getUsername(),jsonLogin.getPassword());
		Authentication authenticate = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		HashMap<String, Object> map = new HashMap<>();
		map.put("status","success");
		return map;
	}
	@GetMapping("/logout")
	@ResponseBody
	public Map<String, Object>logout(){
		SecurityContextHolder.clearContext();
		HashMap<String, Object> map = new HashMap<>();
		map.put("status","success");
		return map;
	}
	@GetMapping("/admin")
	@ResponseBody
	public Map<String,Object> admin(Principal principal){
		if("anonymousUser".equals(principal)){
			return null;
		}
		MyUser user = service.findUserByName(principal.getName());
		List<Role> roles = service.findRolesByUserName(principal.getName());

		return resultMap("Admin",user,roles);
	}
	@GetMapping("/user")
	@ResponseBody
	public Map<String,Object> user(Principal principal){
		if("anonymousUser".equals(principal)){
			return null;
		}
		MyUser user = service.findUserByName(principal.getName());
		List<Role> roles = service.findRolesByUserName(principal.getName());

		return resultMap("User",user,roles);
	}
	@GetMapping("/visitor")
	@ResponseBody
	public Map<String,Object> visitor(HttpSession httpSession){
		return resultMap("Visitor",null,null);
	}
	@PostMapping("/findUserByName")
	@GetMapping("/findUserByName")
	@ResponseBody
	public MyUser findUserByName(String userName){
		if(userName==null||userName.isEmpty()){
			return new MyUser();
		}
		return service.findUserByName(userName);
	}
	@GetMapping("/accessDenied")
	@ResponseBody
	public Map<String,Object> accessDenied(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("msg","Access Denied");
		return map;
	}
	@GetMapping("/notLogin")
	@ResponseBody
	public Map<String,Object> notLogin(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("msg","Not Login");
		return map;
	}


	private Map<String, Object> resultMap(String auth,MyUser user, List<Role> roles){
		HashMap<String, Object> map = new HashMap<>();
		map.put("Auth",auth);
		map.put("UserInfo",user);
		map.put("UserRoles",roles);
		return map;
	}
}
