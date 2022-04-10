package com.company.clients.fraud.notification;

public record NotificationRequest(Integer toCustomerId, String toCustomerName, String message) {
}
