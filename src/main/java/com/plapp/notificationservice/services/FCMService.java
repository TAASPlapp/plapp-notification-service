package com.plapp.notificationservice.services;

import com.plapp.entities.messaging.DiagnosisMQDTO;
import com.plapp.entities.messaging.ScheduleActionMQDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FCMService {
    private final FCMClient fcmClient;

    public FCMService(FCMClient fcmClient) {
        this.fcmClient = fcmClient;
    }

    void sendDiagnosis(DiagnosisMQDTO diagnosisMQDTO, String firebaseToken) {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("title", "Diagnosis result");
            data.put("body", String.format("Disease %s for plant %s", diagnosisMQDTO.getDisease(), diagnosisMQDTO.getPlant()));
            this.fcmClient.send(data, firebaseToken);
        } catch (Exception e) {
            System.err.println("Cannot send message");
            e.printStackTrace();
        }
    }

    void sendSchedule(ScheduleActionMQDTO scheduleActionMQDTO, String firebaseToken) {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("title", "Action needed");
            data.put("body", String.format("Action: %s for plant %s", scheduleActionMQDTO.getAction(), scheduleActionMQDTO.getPlant()));
            this.fcmClient.send(data, firebaseToken);
        } catch (Exception e) {
            System.err.println("Cannot send message");
            e.printStackTrace();
        }
    }

    void sendPushMessage(String json, String type, String firebaseToken) throws InterruptedException, ExecutionException {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("type", type);
            data.put("json", json);
            System.out.println("Sending chuck joke...");
            this.fcmClient.send(data, firebaseToken);
        } catch (Exception e) {
            System.err.println("Cannot send message");
            e.printStackTrace();
        }
    }

}