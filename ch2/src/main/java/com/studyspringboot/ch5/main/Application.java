package com.studyspringboot.ch5.main;

import com.studyspringboot.ch5.dao.MyBatisUserDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = {"com.studyspringboot.ch5"})
//@EnableJpaRepositories(basePackages = "com.studyspringboot.ch5.dao")
//@EntityScan(basePackages = "com.studyspringboot.ch5.pojo")
@MapperScan(
		//指定扫描的包
		basePackages = "com.studyspringboot.ch5.*",
		//指定SqlSessionFactory
		//sqlSessionFactoryRef = "sqlSessionFactory",
		//指定sqlSessionTemplate
		//sqlSessionTemplateRef = "sqlSessionTemplate",
		//限定名扫描接口，不常用
		//markerInterface = Class.class
		//扫描的注解
		annotationClass = Repository.class
)
public class Application {
//	@Autowired
//	SqlSessionFactory sqlSessionFactory =null;
//	@Bean
//	public MapperFactoryBean<MyBatisUserDao> initMyBatisUserDao(){
//		MapperFactoryBean<MyBatisUserDao> bean = new MapperFactoryBean<>();
//		bean.setMapperInterface(MyBatisUserDao.class);
//		bean.setSqlSessionFactory(sqlSessionFactory);
//		return bean;
//	}
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
	}
}
