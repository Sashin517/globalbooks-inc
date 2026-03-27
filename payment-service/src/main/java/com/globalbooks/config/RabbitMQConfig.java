package com.globalbooks.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    // 1. Create the Payment Queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    // 2. Connect to the Topic Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    // 3. Bind the Queue to the Exchange using the Routing Key
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    // 4. JSON Converter
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}