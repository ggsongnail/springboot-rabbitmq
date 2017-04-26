package com.soonfor;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;

//acknowledge-mode = manual
@Service
public class OrderMessageListener {

    private static final Logger log = LoggerFactory.getLogger(OrderMessageListener.class);

    @RabbitListener(queues = QueueApply.QUEUE_GENERIC_NAME)
    public void receiveMessage(final Message message, @Header(AmqpHeaders.CHANNEL) Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
    	try {
    		log.info("Received message as generic: {}", message.toString());
    		channel.basicAck(deliveryTag, false);
    	} catch (Exception e) {
			//消息从新入队    requeue参数设置为false则丢弃此消息
			channel.basicNack(deliveryTag, false, true);
		}
    }

    @RabbitListener(queues = QueueApply.QUEUE_SPECIFIC_NAME)
    public void receiveMessage(final OrderMessage customMessage, @Header(AmqpHeaders.CHANNEL) Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
    	try {
    		log.info("Received message as specific class: {}", customMessage.toString());
    		channel.basicAck(deliveryTag, false);
    	} catch (Exception e) {
			//消息从新入队
			channel.basicNack(deliveryTag, false, true);
		}
    }
    
    @RabbitListener(queues = QueueApply.QUEUE_MENGTIAN_NAME)
    public void receiveMengtianMessage(final OrderMessage customMessage, @Header(AmqpHeaders.CHANNEL) Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
    	try {
    		log.info("Received message as mengtian: {}", customMessage.toString());
    		channel.basicAck(deliveryTag, false);
    	} catch (Exception e) {
			//消息从新入队
			channel.basicNack(deliveryTag, false, true);
		}
    }
    
    
    @RabbitListener(queues = QueueApply.QUEUE_MUSi_NAME)
    public void receiveMusiMessage(final OrderMessage customMessage, @Header(AmqpHeaders.CHANNEL) Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
    	try {
    		log.info("Received message as musi: {}", customMessage.toString());
    		channel.basicAck(deliveryTag, false);
		} catch (Exception e) {
			//消息从新入队
			channel.basicNack(deliveryTag, false, true);
		}
    }
}
