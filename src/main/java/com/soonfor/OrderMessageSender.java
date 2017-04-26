package com.soonfor;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OrderMessageSender {

    private static final Logger log = LoggerFactory.getLogger(OrderMessageSender.class);

    
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderMessageSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendMessage() {
    	String[] keys = {QueueApply.ROUTING_KEY,QueueApply.MENGTIAN_KEY,QueueApply.MUSI_KEY};
    	int i = new Random().nextInt(3);
        final OrderMessage message = new OrderMessage(keys[i], new Random().nextInt(50), false);
        log.info("发送订单：");
        //rabbitTemplate.convertAndSend(MessagingApplication.EXCHANGE_NAME, MessagingApplication.ROUTING_KEY, message);
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(TopicExchangeApply.EXCHANGE_NAME, keys[i], message,correlationId);
    }
}
