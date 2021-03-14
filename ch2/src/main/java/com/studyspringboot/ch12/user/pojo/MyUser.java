package com.studyspringboot.ch12.user.pojo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class MyUser {
	private String userName;
	private String pwd;
	private String note;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
