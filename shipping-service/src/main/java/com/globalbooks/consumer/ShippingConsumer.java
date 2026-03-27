package com.globalbooks.consumer;

import com.globalbooks.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ShippingConsumer {

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeOrderMessage(Order order) {
        System.out.println("\n----------------------------------------");
        System.out.println("SHIPPING PROCESS INITIATED!");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Customer: " + order.getCustomerId());
        System.out.println("Books to Pack: " + order.getBookIds());
        System.out.println("Status: READY FOR DISPATCH");
        System.out.println("----------------------------------------\n");
    }
}