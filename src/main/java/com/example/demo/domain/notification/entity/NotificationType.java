package com.example.demo.domain.notification.entity;

public enum NotificationType {
    MISSION, // 미션에 대한 알람( 문제를 맞고 틀리고, 연속 정답, 오랜 기간 미션 활동이 없는 경우 )
    COMMUNITY, // 커뮤니티에 대한 알람( 커뮤니티 참여, 커뮤니티 탈퇴 )
    COMMENT, // 댓글에 대한 알람( 게시글에 대한 댓글 달린 경우)
    SYSTEM // 시스템에 대한 알람(로그인, 탈퇴)
}