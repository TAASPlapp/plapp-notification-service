package com.plapp.notificationservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Setter
@Getter
@Configuration
@PropertySource("classpath:application.properties")
public class RabbitMQConfig {
    @Value("${mq.notification.queue}")
    private String notificationQueue;

    @Value("${mq.notification.exchange}")
    private String notificationExchange;

    @Value("${mq.notification.routingKey}")
    private String notificationRoutingKey;
}