package com.studyspringboot.ch12.user.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@SpringBootApplication(scanBasePackages = {"com.studyspringboot.ch12.user"}
)
@EnableCaching
@EnableWebSecurity
@MapperScan(
		basePackages = "com.studyspringboot.ch12.user.dao",
		annotationClass = Repository.class
)
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}

	@Bean
	public RedisCacheConfiguration initRedisCacheConfiguration(){
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		config=config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));
		config=config.entryTtl(Duration.ofMinutes(5));
		return config;
	}

}
