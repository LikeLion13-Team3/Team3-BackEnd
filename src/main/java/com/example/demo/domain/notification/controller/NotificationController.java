package com.example.demo.domain.notification.controller;

import com.example.demo.domain.notification.dto.NotificationResponseDto.NotificationResponseDto;
import com.example.demo.domain.notification.service.command.NotificationCommandService;
import com.example.demo.domain.notification.service.query.NotificationQueryService;
import com.example.demo.global.dto.ApiResponse;
import com.example.demo.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationQueryService notificationQueryService;
    private final NotificationCommandService notificationCommandService;
    private final JwtUtil jwtUtil;

    @GetMapping
    public ApiResponse<List<NotificationResponseDto>> getNotifications(@RequestHeader("Authorization") String bearerToken) {
        String loginId = jwtUtil.getLoginId(bearerToken);
        List<NotificationResponseDto> notifications = notificationQueryService.getNotifications(loginId);
        return ApiResponse.success("알림 목록 조회 성공", notifications);
    }

    @PostMapping("/{notificationId}/read")
    public ApiResponse<Void> readNotification(@RequestHeader("Authorization") String bearerToken,
                                              @PathVariable Long notificationId) {
        String loginId = jwtUtil.getLoginId(bearerToken);
        notificationCommandService.readNotification(loginId, notificationId);
        return ApiResponse.success("알림을 읽음 처리했습니다.", null);
    }
}