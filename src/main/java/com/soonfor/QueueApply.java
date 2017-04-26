package com.soonfor;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class QueueApply {
	public static final String QUEUE_GENERIC_NAME = "SFGenericQueue";//通常格式
	public static final String QUEUE_SPECIFIC_NAME = "SFSpecificQueue";//特殊格式比如json
	
	public static final String QUEUE_MENGTIAN_NAME = "梦天队列";//梦天队列SFMengTianQueue
	public static final String QUEUE_MUSi_NAME = "慕思队列";//慕思队列SFMusiQueue
	
	public static final String ROUTING_KEY = "messages.key";
	public static final String MENGTIAN_KEY = "mengtian";//梦天标识KEY
	public static final String MUSI_KEY = "musi";//慕思标识KEY
	
	private final TopicExchange topicExchange;
	
	@Autowired
	public QueueApply(final TopicExchange topicExchange){
		this.topicExchange = topicExchange;
	}
	
	//默认持久化
	@Bean
	public Queue appQueueGeneric() {
		return new Queue(QUEUE_GENERIC_NAME);
	}

	@Bean
	public Queue appQueueSpecific() {
		return new Queue(QUEUE_SPECIFIC_NAME);
	}
	
	//声明梦天队列
	@Bean
	public Queue appQueueMengtian() {
		return new Queue(QUEUE_MENGTIAN_NAME);
	}
	
	//声明慕思队列
	@Bean
	public Queue appQueueMusi() {
		return new Queue(QUEUE_MUSi_NAME);
	}
	
	@Bean
	public Binding declareBindingGeneric() {
		return BindingBuilder.bind(appQueueGeneric()).to(topicExchange).with(ROUTING_KEY);
	}

	@Bean
	public Binding declareBindingSpecific() {
		return BindingBuilder.bind(appQueueSpecific()).to(topicExchange).with(ROUTING_KEY);
	}
	
	@Bean
	public Binding declareBindingMengTian() {
		return BindingBuilder.bind(appQueueMengtian()).to(topicExchange).with(MENGTIAN_KEY);
	}

	@Bean
	public Binding declareBindingMusi() {
		return BindingBuilder.bind(appQueueMusi()).to(topicExchange).with(MUSI_KEY);
	}
}
