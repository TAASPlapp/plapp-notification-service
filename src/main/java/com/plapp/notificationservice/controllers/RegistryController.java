package com.plapp.notificationservice.controllers;

import com.plapp.notificationservice.entities.NotificationServiceRegistration;
import com.plapp.notificationservice.repositories.NotificationServiceRegistrationRepository;
import com.plapp.notificationservice.services.FCMClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("/notifications")
public class RegistryController {
    private final Logger logger = LoggerFactory.getLogger(RegistryController.class);

    private final FCMClient fcmClient;
    @Autowired
    private NotificationServiceRegistrationRepository notificationServiceRegistrationRepository;

    public RegistryController(FCMClient fcmClient) {
        this.fcmClient = fcmClient;
    }

    @GetMapping("/{userId}/register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void register(@PathVariable ("userId") long userId, @RequestParam long sessionId, @RequestParam String firebaseToken) throws InterruptedException, ExecutionException  {
        logger.info("Registering user {} sessionId {} with token {}", userId, sessionId, firebaseToken);
        this.fcmClient.subscribe("plapp",firebaseToken);
        NotificationServiceRegistration notificationServiceRegistration = new NotificationServiceRegistration(sessionId,userId,firebaseToken);
        notificationServiceRegistrationRepository.save(notificationServiceRegistration);
    }

}