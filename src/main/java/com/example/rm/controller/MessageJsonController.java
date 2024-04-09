package com.example.rm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rm.dto.User;
import com.example.rm.publisher.RabbitMQJsonProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {
    public RabbitMQJsonProducer rabbitMQJsonProducer;
    MessageJsonController(RabbitMQJsonProducer rabbitMQJsonProducer){
        rabbitMQJsonProducer=this.rabbitMQJsonProducer;
    }
    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        rabbitMQJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json message sending to rabbitmq...");
    }

    
}
