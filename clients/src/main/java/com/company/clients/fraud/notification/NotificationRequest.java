package com.company.clients.fraud.notification;

public record NotificationRequest(Integer toCustomerId, String customerEmail,String toCustomerName, String message) {
}
