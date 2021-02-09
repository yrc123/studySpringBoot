package com.studyspringboot.ch2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan
public class UserConfig {
    @Bean(name="user")
    public User initUser(){
        User user= new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("pass");
        return user;
    }
    //从properties注入
    @Bean(name="propUser")
    public User porpUser(){
        return new User();
    }
}
