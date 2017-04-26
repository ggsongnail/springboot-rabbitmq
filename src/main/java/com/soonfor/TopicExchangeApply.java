package com.soonfor;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

public class TopicExchangeApply {
	public static final String EXCHANGE_NAME = "SFOrderExchange";
	
	@Bean
	public TopicExchange appExchange() {
		final TopicExchange topicExchange = new TopicExchange(EXCHANGE_NAME);
		return topicExchange;
	}
}
