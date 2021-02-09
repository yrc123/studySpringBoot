package com.studyspringboot.ch6;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(
		annotationClass = Repository.class
)
public class Application {
	@Autowired
	UserDao userDao = null;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
