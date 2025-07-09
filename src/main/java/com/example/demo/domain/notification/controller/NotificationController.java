package com.example.demo.domain.notification.controller;

import com.example.demo.domain.notification.dto.NotificationResponseDto.NotificationResponseDto;
import com.example.demo.domain.notification.service.command.NotificationCommandService;
import com.example.demo.domain.notification.service.query.NotificationQueryService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "알람", description = "알람 관련 API")
public class NotificationController {

    private final NotificationQueryService notificationQueryService;
    private final NotificationCommandService notificationCommandService;
    private final JwtUtil jwtUtil;

    @GetMapping
    @Operation(summary = "알람 목록 조회 API", description = "알람 목록을 조회합니다.")
    public ApiResponse<List<NotificationResponseDto>> getNotifications(@RequestHeader("Authorization") String bearerToken) {
        try {
            System.out.println("[알림 목록 조회] 컨트롤러 도달");

            if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
                throw new RuntimeException("잘못된 토큰 형식");
            }

            String token = bearerToken.substring(7); // "Bearer " 제거
            String loginId = jwtUtil.getLoginId(token); // 순수 토큰 전달
            System.out.println("loginId: " + loginId);

            List<NotificationResponseDto> notifications = notificationQueryService.getNotifications(loginId);
            return ApiResponse.onSuccess("알림 목록 조회 성공", notifications);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("서버 내부 오류 발생");
        }
    }

    @PostMapping("/{notificationId}/read")
    @Operation(summary = "알람 읽음 처리 API", description = "알람 읽음 처리합니다.")
    public ApiResponse<Void> readNotification(@RequestHeader("Authorization") String bearerToken,
                                              @PathVariable Long notificationId) {
        try {
            System.out.println("[readNotification] 호출됨: notificationId = " + notificationId);

            if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
                throw new RuntimeException("잘못된 토큰 형식");
            }
            String token = bearerToken.substring(7);
            String loginId = jwtUtil.getLoginId(token);
            System.out.println("loginId: " + loginId);

            notificationCommandService.readNotification(loginId, notificationId);

            return ApiResponse.onSuccess("알림을 읽음 처리했습니다.", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("서버 내부 오류 발생");
        }
    }

}