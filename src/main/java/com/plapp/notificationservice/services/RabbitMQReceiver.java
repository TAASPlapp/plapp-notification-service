package com.plapp.notificationservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plapp.entities.messaging.DiagnosisMQDTO;
import com.plapp.entities.messaging.ScheduleActionMQDTO;
import com.plapp.notificationservice.config.RabbitMQConfig;
import com.plapp.notificationservice.entities.DiagnosisNotification;
import com.plapp.notificationservice.entities.NotificationServiceRegistration;
import com.plapp.notificationservice.entities.ScheduleActionNotification;
import com.plapp.notificationservice.repositories.NotificationServiceRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.plapp.entities.schedules.ScheduleAction;
import com.plapp.entities.schedules.Diagnosis;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class RabbitMQReceiver {
    private final RabbitMQConfig rabbitMQConfig;

    @Autowired
    FCMService fcmService;

    @Autowired
    private NotificationServiceRegistrationRepository notificationServiceRegistrationRepository;

    @Bean
    public Queue notificationQueue() {
        return new Queue(rabbitMQConfig.getNotificationQueue(), false);
    }

    @Bean
    TopicExchange notificationExchange() {
        return new TopicExchange(rabbitMQConfig.getNotificationExchange());
    }

    @Bean
    Binding notificationBinding(@Qualifier("notificationQueue") Queue queue, @Qualifier("notificationExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(rabbitMQConfig.getNotificationRoutingKey());
    }

    //These are all the possible types of Objects that can be received through the "gardener.queue" queue.
    private final String scheduleActionClass = "com.plapp.entities.schedules.ScheduleActionMQDTO";
    private final String diagnosisClass = "com.plapp.entities.schedules.DiagnosisMQDTO";

    @RabbitListener(queues = "${mq.notification.queue}")
    public void receiveMessage(final Message message) throws IOException, ExecutionException, InterruptedException {
        System.out.println("Received message: " + message);

        ObjectMapper objectMapper = new ObjectMapper();
        String messageBody = new String(message.getBody());

        if(message.getMessageProperties().getHeaders().containsValue(scheduleActionClass)){
            ScheduleActionMQDTO scheduleActionNotification = objectMapper.readValue(messageBody,ScheduleActionMQDTO.class);
            System.out.println("Received ScheduleActionMQDTO: " + scheduleActionNotification);
            for(NotificationServiceRegistration nsr : notificationServiceRegistrationRepository
                    .findAllByUserId(scheduleActionNotification
                            .getPlant().getOwner())){
                fcmService.sendPushMessage(messageBody,"schedule",nsr.getFirebaseToken());
            }
        }
        else if(message.getMessageProperties().getHeaders().containsValue(diagnosisClass)){
            DiagnosisMQDTO diagnosisNotification = objectMapper.readValue(messageBody,DiagnosisMQDTO.class);
            System.out.println("Received DiagnosisMQTDO: " + diagnosisNotification);
            for(NotificationServiceRegistration nsr : notificationServiceRegistrationRepository
                    .findAllByUserId(diagnosisNotification
                            .getPlant().getOwner())){
                fcmService.sendPushMessage(messageBody,"diagnosis",nsr.getFirebaseToken());
            }
        }
    }

}
