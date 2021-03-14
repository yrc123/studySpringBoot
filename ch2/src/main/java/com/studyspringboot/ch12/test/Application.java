package com.studyspringboot.ch12.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableWebSecurity
public class Application extends WebSecurityConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.inMemoryAuthentication()
				.passwordEncoder(passwordEncoder)
				.withUser("admin")
				.password("123")
				.roles("USER","ADMIN")
				.and()
				.withUser("user")
				.password(passwordEncoder.encode("123"))
				.roles("USER");
	}
}
