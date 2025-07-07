package com.example.demo.domain.notification.service.command;

public interface NotificationCommandService {
    void readNotification(String loginId, Long notificationId);
}
