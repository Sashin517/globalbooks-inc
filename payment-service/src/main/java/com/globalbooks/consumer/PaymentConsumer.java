package com.globalbooks.consumer;

import com.globalbooks.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    // Listen to the specific queue defined in application.properties
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeOrderMessage(Order order) {
        System.out.println("\n========================================");
        System.out.println("NEW ORDER RECEIVED IN PAYMENT SERVICE!");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Customer: " + order.getCustomerId());
        System.out.println("Amount to Charge: $" + order.getTotalAmount());
        System.out.println("Status: PAYMENT PROCESSED SUCCESSFULLY");
        System.out.println("========================================\n");
    }
}