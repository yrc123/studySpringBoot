package com.studyspringboot.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person {
	@Autowired
	@Qualifier("cat")
	private Animal animal = null;
	public void service(){
		this.animal.use();
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
}
