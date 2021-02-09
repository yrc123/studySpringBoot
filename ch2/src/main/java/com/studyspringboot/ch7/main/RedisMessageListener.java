package com.studyspringboot.ch7.main;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

//自定义监听器
@Component
public class RedisMessageListener implements MessageListener {
	@Override
	public void onMessage(Message message, byte[] pattern){
		//消息体
		String body = new String(message.getBody());
		//消息渠道
		String topic = new String(pattern);
		System.out.println(body);
		System.out.println(topic);
	}
}
