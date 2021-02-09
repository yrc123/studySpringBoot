package com.studyspringboot.ch4.AOP.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"com.studyspringboot.ch4.AOP.*"})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
