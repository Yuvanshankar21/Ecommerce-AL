package com.example.notification.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void consume(String message) {
        System.out.println("Received Order Event: " + message);
        emailService.sendEmail("yuvanshankar2111@gmail.com", "Order Confirmation", message);
    }
}