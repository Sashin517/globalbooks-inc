package com.globalbooks.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    // Create the Exchange in RabbitMQ
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    // Convert Java Objects to JSON formats for RabbitMQ
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    // Configure the RabbitTemplate to use the JSON converter
    @Bean
    public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}