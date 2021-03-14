package com.studyspringboot.ch12.user.pojo;

import org.springframework.stereotype.Component;

@Component
public class Role {
	private String roleName;
	private String note;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
