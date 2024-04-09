package com.example.rm.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

import com.example.rm.dto.User;

public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routingKey.json.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
    
    private RabbitTemplate rabbitTemplate;
    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendJsonMessage(User user){
        LOGGER.info(String.format("Json message sent:", user.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
    }

    
}
