package com.example.rm.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routingKey.name}")
    private String routingKey;

    // SpringBoot automatically creates template for us We just to need inject and use that
    // Constructor based Injector 
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private RabbitTemplate rabbitTemplate;



    public RabbitMQProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;    
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("Message sent ----> %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);

    }
}
