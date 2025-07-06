package com.example.demo.domain.notification.dto.NotificationResponseDto;

import com.example.demo.domain.notification.entity.Notification;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationResponseDto {
    private Long id;
    private String type;
    private String content;
    private Boolean isRead;
    private LocalDateTime createdAt;

    public NotificationResponseDto(Long id, String type, String content, boolean isRead, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }

    public static NotificationResponseDto from(Notification notification) {
        return NotificationResponseDto.builder()
                .id(notification.getId())
                .type(notification.getType().name())
                .content(notification.getContent())
                .isRead(notification.getIsRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}
