package com.plapp.notificationservice.services;

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

    void sendPushMessage(String json, String type, String firebaseToken) throws InterruptedException, ExecutionException {
        Map<String, String> data = new HashMap<>();
        data.put("type", type);
        data.put("json", json);
        System.out.println("Sending chuck joke...");
        this.fcmClient.send(data, firebaseToken);
    }

}