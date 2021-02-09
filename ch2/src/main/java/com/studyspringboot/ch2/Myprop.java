package com.studyspringboot.ch2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component("prop")
@Conditional(MyConditional.class)
@ConfigurationProperties(prefix = "myprop")
public class Myprop {
	int id;
	String username;
	String password;
	String noPassword;



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNoPassword() {
		return noPassword;
	}

	public void setNoPassword(String noPassword) {
		this.noPassword = noPassword;
	}
}
