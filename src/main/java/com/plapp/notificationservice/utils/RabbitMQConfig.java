package com.plapp.notificationservice.utils;

import com.plapp.notificationservice.services.RabbitMQReceiver;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;

public class RabbitMQConfig {
    @Bean
    MessageListenerAdapter listenerAdapter(RabbitMQReceiver receiver) {
        return new MessageListenerAdapter(receiver, "messageHandler");
    }
}
