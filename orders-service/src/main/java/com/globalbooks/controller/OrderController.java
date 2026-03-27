package com.globalbooks.controller;

import com.globalbooks.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private Map<String, Order> database = new HashMap<>();

    // Inject RabbitTemplate to send messages
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    // Constructor injection
    public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        // 1. Save order to the database
        String newId = UUID.randomUUID().toString();
        order.setId(newId);
        database.put(newId, order);

        // 2. Send the order object to RabbitMQ
        rabbitTemplate.convertAndSend(exchange, routingKey, order);
        System.out.println("Order successfully sent to RabbitMQ! Order ID: " + order.getId());

        return order;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) {
        return database.get(id);
    }
}