package com.plapp.notificationservice.repositories;

import com.plapp.notificationservice.entities.NotificationServiceRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationServiceRegistrationRepository extends JpaRepository<NotificationServiceRegistration, Long> {
    public List<NotificationServiceRegistration> findAllByUserId(long userId);
}
