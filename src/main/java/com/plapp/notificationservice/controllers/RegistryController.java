package com.plapp.notificationservice.controllers;

import com.plapp.notificationservice.entities.NotificationServiceRegistration;
import com.plapp.notificationservice.repositories.NotificationServiceRegistrationRepository;
import com.plapp.notificationservice.services.FCMClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/notifications")
public class RegistryController {

    private final FCMClient fcmClient;
    @Autowired
    private NotificationServiceRegistrationRepository notificationServiceRegistrationRepository;

    public RegistryController(FCMClient fcmClient) {
        this.fcmClient = fcmClient;
    }

    @PostMapping("/{userId}/register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void register(@PathVariable ("userId") long userId, @RequestParam long sessionId, @RequestParam String firebaseToken) {
        this.fcmClient.subscribe("plapp",firebaseToken);
        NotificationServiceRegistration notificationServiceRegistration = new NotificationServiceRegistration(sessionId,userId,firebaseToken);
        notificationServiceRegistrationRepository.save(notificationServiceRegistration);
        //return token.doOnNext(t -> this.fcmClient.subscribe("chuck", exToken)).then();//t)).then();
    }

}