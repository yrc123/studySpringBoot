package com.studyspringboot.ch7.mycache.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"com.studyspringboot.ch7.mycache"})
@MapperScan(basePackages = "com.studyspringboot.ch7.mycache", annotationClass = Repository.class)
@EnableCaching
public class Application {
	@Autowired
	private RedisConnectionFactory connectionFactory = null;
//	@Autowired
//	private RedisTemplate redisTemplate = null;
//	@PostConstruct
//	public void init(){
//		initRedisTemplate();
//	}
//	private void initRedisTemplate(){
//		RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
//		redisTemplate.setKeySerializer(stringSerializer);
//		redisTemplate.setValueSerializer(RedisSerializer.json());
//		redisTemplate.setStringSerializer(stringSerializer);
//		redisTemplate.setHashKeySerializer(stringSerializer);
//		System.out.println("Serializer");
//	}

	//自定义Redis缓存管理器
	@Bean(name = "cacheManager")
	@Primary
	public CacheManager initRedisCacheManager(){
		//Redis加入写入锁
		RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
		//启动Redis缓存默认配置
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		//设置序列化器为GenericJackson2JsonRedisSerializer
		config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
		RedisCacheManager redisCacheManager = new RedisCacheManager(writer, config);
		return redisCacheManager;
		//通过builder
//		return RedisCacheManager.builder(connectionFactory).cacheDefaults(config).build();
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
