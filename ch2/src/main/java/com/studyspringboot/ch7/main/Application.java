package com.studyspringboot.ch7.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"com.studyspringboot.ch7.main"})
public class Application {
	@Autowired
	RedisTemplate redisTemplate = null;
	@Autowired
	RedisConnectionFactory connectionFactory = null;
	@Autowired
	RedisMessageListener redisMessageListener = null;

	@Bean
	public RedisMessageListenerContainer initRedisContainer(){
		//新建连接器
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		//设置连接工厂
		container.setConnectionFactory(connectionFactory);
		//新建消息渠道
		Topic topic = new ChannelTopic(("topic1"));
		//添加监听器
		container.addMessageListener(redisMessageListener,topic);
		//自动装配
		return container;
	}

	@PostConstruct
	public void init(){
		initRedisTemplate();
	}
	public void initRedisTemplate(){
		//设置序列化器
		RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
//		redisTemplate.setValueSerializer(RedisSerializer.string());
		redisTemplate.setValueSerializer(RedisSerializer.json());
		redisTemplate.setHashKeySerializer(stringSerializer);
		System.out.println("setStringSerializer");
	}
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class,args);
	}
}
