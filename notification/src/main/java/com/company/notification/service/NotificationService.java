package com.company.notification.service;

import com.company.clients.fraud.notification.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    public void send(NotificationRequest notificationRequest);
}
