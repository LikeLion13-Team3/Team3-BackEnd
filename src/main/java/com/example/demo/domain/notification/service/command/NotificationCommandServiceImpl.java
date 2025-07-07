package com.example.demo.domain.notification.service.command;

import com.example.demo.domain.notification.entity.Notification;
import com.example.demo.domain.notification.repository.NotificationRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.global.exception.NotFoundException;
import com.example.demo.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class NotificationCommandServiceImpl implements NotificationCommandService {

    private final NotificationRepository notificationRepository;
    private final UserUtil userUtil;

    @Transactional
    @Override
    public void readNotification(String loginId, Long notificationId) {
        User user = userUtil.getUserByLoginId(loginId);
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotFoundException("알림을 찾을 수 없습니다."));

        if (!notification.getUser().getId().equals(user.getId())) {
            throw new NotFoundException("해당 알림에 접근 권한이 없습니다.");
        }

        notification.setIsRead(true);
    }
}

