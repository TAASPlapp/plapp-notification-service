package com.plapp.notificationservice.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NotificationServiceRegistration {

    @Id
    private long sessionId;
    private long userId;
    private String firebaseToken;

    public NotificationServiceRegistration() {
    }

    public NotificationServiceRegistration(long sessionId, long userId, String firebaseToken) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.firebaseToken = firebaseToken;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}
