package com.studyspringboot.ch12.user.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyspringboot.ch12.user.service.TokenService;
import com.studyspringboot.ch12.user.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Component
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;
//	@Autowired
//	TokenService tokenService;
//	@Autowired
//	ExceptionTranslationFilter exceptionTranslationFilter;

	@Value("${system.user.password.secret}")
	private String secret = null;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = new Pbkdf2PasswordEncoder(secret);
//		System.out.println(encoder.encode("123"));
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.rememberMe().tokenRepository(tokenService);
		http.rememberMe().alwaysRemember(true);
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//		http.authorizeRequests().anyRequest().authenticated();
		http.authorizeRequests()
//			.antMatchers("/visitor","/login","/notLogin","/AccessDenied").permitAll()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("ADMIN","USER")
			.antMatchers("/logout").authenticated()
			.anyRequest().permitAll();
//		http.formLogin().loginProcessingUrl("/dologin").successHandler(new AuthenticationSuccessHandler() {
//			@Override
//			public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//				PrintWriter writer = httpServletResponse.getWriter();
//				writer.write(new ObjectMapper().writeValueAsString("sucess"));
//				writer.flush();
//				writer.close();
//			}
//		});

		http.formLogin().disable();
//		http.formLogin();
		http.logout().disable();

		//设置登录后权限不足的页面
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		//设置未登录时的页面
		//LoginUrlAuthenticationEntryPoint是一个AuthenticationEntryPoint的实现类，可以定义登录页面，会自动重定向到登录页
		AuthenticationEntryPoint authenticationEntryPoint = new LoginUrlAuthenticationEntryPoint("/login.html");
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

	}
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

//	@PostConstruct
//	public void init(){
//		AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandler() {
//			@Override
//			public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//				PrintWriter writer = httpServletResponse.getWriter();
//				writer.write("Access Denied");
//				writer.flush();
//				writer.close();
//			}
//		};
//		exceptionTranslationFilter.setAccessDeniedHandler(accessDeniedHandler);
//		FilterSecurityInterceptor
//	}
//	@Bean
//	public AccessDeniedHandler initAccessDeniedHandler(){
//		AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
//		accessDeniedHandler.setErrorPage("/visitor");
//		return accessDeniedHandler;
//	}
}
