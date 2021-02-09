package com.studyspringboot.ch4.AOP.aspect;

import com.studyspringboot.ch4.AOP.User;
import com.studyspringboot.ch4.AOP.service.UserValidator;
import com.studyspringboot.ch4.AOP.service.UserValidatorImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
	//引入增强类
	@DeclareParents(value="com.studyspringboot.ch4.AOP.service.UserServerImpl",
			defaultImpl= UserValidatorImpl.class)
	public UserValidator userValidator;

	//匹配表达式
	@Pointcut("execution( * com.studyspringboot.ch4.AOP.service.UserServerImpl.*(..))")
	public void pointCut(){}

	//JoinPoint包含了被代理方法的所有参数
	@Before("pointCut()")
	public void before(JoinPoint jp){
		Object[] args = jp.getArgs();
		System.out.println("before ...");
	}

	//user为被代理方法传入的的user参数
	@After("pointCut() && args(user)")
	public void after(User user){
		System.out.println("after ...");
	}
	@AfterReturning("pointCut()")
	public void afterReturning(){
		System.out.println("afterReturning ...");
	}

	@AfterThrowing("pointCut()")
	public void afterThrowing(){
		System.out.println("afterThrowing ...");
	}
	@Around("pointCut()")
	public void around(ProceedingJoinPoint pj) throws Throwable {
		System.out.println("around before ...");
		pj.proceed();
		System.out.println("around after ...");
	}
}
