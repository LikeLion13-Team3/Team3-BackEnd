package com.example.demo.domain.community.entity;

import com.example.demo.domain.exam.entity.Exam;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String communityName; // 커뮤니티 이름 ( ex> 00 자격증 시험의 커뮤니티 )

    @OneToMany(mappedBy = "community")
    private List<UserCommunity> userCommunities = new ArrayList<>();

    @OneToMany(mappedBy = "community")
    private List<Post> posts = new ArrayList<>();

    @OneToOne(mappedBy = "community")
    private Mission mission;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false, unique = true)
    private Exam exam;
}
