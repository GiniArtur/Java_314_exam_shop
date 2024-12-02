package com.example.buysell.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;
@RequiredArgsConstructor
@RestController


public class SendRAbbController {
    Logger log = Logger.getLogger(SendRAbbController.class.getName());

    private final AmqpTemplate amqpTemplate;
    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/emit")

    public ResponseEntity<String> emit(@RequestBody String message) {
        log.info("Emit message to queue: " + message);
        // Попробуйте отправить сообщение
        try {
            amqpTemplate.convertAndSend("myQueue", message);
            System.out.println("Message sent to queue: " + message);
            return ResponseEntity.ok("Сообщение отправлено: " + message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Ошибка отправки сообщения: " + e.getMessage());
        }
    }
}
