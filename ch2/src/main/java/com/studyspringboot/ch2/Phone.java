package com.studyspringboot.ch2;

import org.springframework.stereotype.Component;

@Component
public class Phone {
	public String use(){
		return "use phone.";
	}
	public String call(String num){
		return "call: "+num;
	}
	public String show(Phone phone){
		return phone.toString();
	}
}
