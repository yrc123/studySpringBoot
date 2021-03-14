package com.studyspringboot.ch10.inteceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//注册拦截器到MVC机制，然会一个拦截器的注册
		InterceptorRegistration ir = registry.addInterceptor(new MyInterceptor());
		//指定拦截匹配模式
		ir.addPathPatterns("/interceptor/*");
	}
}
