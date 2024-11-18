package com.hqm.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedisApplication {

	@Bean
	JedisConnectionFactory jedisConnectionFactory(){
		JedisConnectionFactory connectionFactory =  new JedisConnectionFactory();
		connectionFactory.setHostName("localhost");
		connectionFactory.setPort(6379);
		return  connectionFactory;

	}

	@Bean
	RedisTemplate<String, Object> redisTemplate(){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return  redisTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

}
