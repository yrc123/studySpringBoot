package com.studyspringboot.ch4;

public class HelloServiceImpl implements HelloService{
	@Override
	public void sayHello(String name) {
		if(name==null||name.trim()==""){
			throw new RuntimeException("name is null");
		}
		System.out.println(name);
	}
}
