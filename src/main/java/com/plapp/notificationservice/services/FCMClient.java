package com.plapp.notificationservice.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.firebase.messaging.*;
import com.plapp.notificationservice.controllers.RegistryController;
import com.plapp.notificationservice.utils.FCMInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
//Firebase Cloud Messaging = FCM

@Service
public class FCMClient {
    private final Logger logger = LoggerFactory.getLogger(FCMClient.class);

    public FCMClient(FCMInitializer settings) throws IOException {
        String path = settings.getServiceAccountFile();
        logger.info("Loading {} from ClassPathResource", path);

        InputStream serviceAccount = new ClassPathResource(path).getInputStream();
        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(
                GoogleCredentials.fromStream(serviceAccount)
        ).build();
        FirebaseApp.initializeApp(options);

        logger.info("FCM App initialized");
    }

    //firebaseToken := token che identifica il destinatario del messaggio
    public void send(Map<String, String> data, String firebaseToken)
            throws InterruptedException, ExecutionException {

        Message message = Message.builder()
                .putAllData(data)
                .setToken(firebaseToken)
                .build();
        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
        logger.info("Sent message {}; got response {}", data, response);
    }

    public void subscribe(String topic, String clientToken) throws InterruptedException, ExecutionException {
        TopicManagementResponse response = FirebaseMessaging
                .getInstance()
                .subscribeToTopicAsync(Collections.singletonList(clientToken), topic).get();
        System.out.println(response.getSuccessCount() + " tokens were subscribed successfully");
    }
}