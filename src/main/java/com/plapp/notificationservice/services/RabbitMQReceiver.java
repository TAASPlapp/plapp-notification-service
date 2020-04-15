package com.plapp.notificationservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plapp.entities.messaging.ScheduleActionMQDTO;
import com.plapp.notificationservice.entities.DiagnosisNotification;
import com.plapp.notificationservice.entities.NotificationServiceRegistration;
import com.plapp.notificationservice.entities.ScheduleActionNotification;
import com.plapp.notificationservice.repositories.NotificationServiceRegistrationRepository;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.plapp.entities.schedules.ScheduleAction;
import com.plapp.entities.schedules.Diagnosis;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class RabbitMQReceiver {

    @Autowired
    FCMService fcmService;
    @Autowired
    private NotificationServiceRegistrationRepository notificationServiceRegistrationRepository;

    //These are all the possible types of Objects that can be received through the "gardener.queue" queue.
    private final String scheduleActionClass = "com.plapp.entities.schedules.ScheduleActionMQDTO";
    private final String diagnosisClass = "com.plapp.entities.schedules.DiagnosisMQDTO";

    @RabbitListener(queues = "gardener.queue")
    public void receiveMessage(final Message message) throws IOException, ExecutionException, InterruptedException {
        //fcmService.sendPushMessage("This IS a JOKE!");
        ObjectMapper objectMapper = new ObjectMapper();
        String messageBody = new String(message.getBody());

        if(message.getMessageProperties().getHeaders().values().contains(scheduleActionClass)){
            ScheduleActionNotification scheduleActionNotification = objectMapper.readValue(messageBody,ScheduleActionNotification.class);
            for(NotificationServiceRegistration nsr : notificationServiceRegistrationRepository
                    .findAllByUserId(scheduleActionNotification
                            .getUserId())){
                fcmService.sendPushMessage(messageBody,"diagnosis",nsr.getFirebaseToken());
            }
        }
        else if(message.getMessageProperties().getHeaders().values().contains(diagnosisClass)){
            DiagnosisNotification diagnosisNotification = objectMapper.readValue(messageBody,DiagnosisNotification.class);
            for(NotificationServiceRegistration nsr : notificationServiceRegistrationRepository
                    .findAllByUserId(diagnosisNotification
                            .getUserId())){
                fcmService.sendPushMessage(messageBody,"diagnosis",nsr.getFirebaseToken());
            }

        }
    }

}
