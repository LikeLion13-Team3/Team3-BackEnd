package com.example.demo.domain.notification.service.query;


import com.example.demo.domain.notification.dto.NotificationResponseDto.NotificationResponseDto;

import java.util.List;

public interface NotificationQueryService {
    List<NotificationResponseDto> getNotifications(String loginId);
}