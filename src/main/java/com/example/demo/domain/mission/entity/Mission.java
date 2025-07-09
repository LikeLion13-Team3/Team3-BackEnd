package com.example.demo.domain.mission.entity;

import com.example.demo.domain.community.entity.Community;
import com.example.demo.domain.notification.entity.NotificationType;
import com.example.demo.domain.problem.entity.Problem;
import com.example.demo.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title; // 미션 제목 (예: 00 자격증 시험의 미션)

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", unique = true, nullable = false)
    private Community community;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Problem> problems = new ArrayList<>();
}