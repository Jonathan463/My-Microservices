package com.company.notification.service;

import com.company.clients.fraud.notification.NotificationRequest;

public interface NotificationService {
    public void send(NotificationRequest notificationRequest);
}
