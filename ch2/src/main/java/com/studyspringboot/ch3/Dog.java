package com.studyspringboot.ch3;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal{
	@Override
	public void use() {
		System.out.println("is dog");
	}
}
