package com.example.rm.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


@Configurable
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routingKey.name}")
    private String routingKey;
    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;
    @Value("${rabbitmq.routingKey.json.name}")
    private String routingJsonKey;
    //spring bean for rabbitmq queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }
    // spring bean for queue to store the json message 
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);

    }

    // Now after creating two java bean ie. queue and exchange We need to bind them using the routing eky
    @Bean 
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue()).to(exchange()).with(routingJsonKey);
    }
    @Bean //Factory type
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
    // We further require these 3 beans  
    // ConnectionFactory
    // RabbitTemplate 
    // RabbitAdmin 
    // But spring boot auto configures automatically those above 3 infrastructure beans 
    // Just we need to use those 


    
}
