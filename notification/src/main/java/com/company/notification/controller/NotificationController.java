package com.company.notification.controller;

import com.company.clients.fraud.notification.NotificationRequest;
import com.company.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("api/v1/notification")
    public void sendNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("New notification... {}", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
