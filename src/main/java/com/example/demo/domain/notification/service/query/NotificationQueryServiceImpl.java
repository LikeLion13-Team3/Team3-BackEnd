package com.example.demo.domain.notification.service.query;


import com.example.demo.domain.notification.dto.NotificationResponseDto.NotificationResponseDto;
import com.example.demo.domain.notification.repository.NotificationRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationRepository notificationRepository;
    private final UserUtil userUtil;

    @Override
    public List<NotificationResponseDto> getNotifications(String loginId) {
        User user = userUtil.getUserByLoginId(loginId);
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(user.getId())
                .stream()
                .map(NotificationResponseDto::from)
                .toList();
    }
}