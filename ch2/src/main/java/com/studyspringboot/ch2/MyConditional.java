package com.studyspringboot.ch2;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyConditional implements Condition {

	@Override
	public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
		Environment evn = conditionContext.getEnvironment();
		return evn.containsProperty("myprop.password");
				//&&evn.containsProperty("myprop.noPassowrd");//不存在
	}
}
