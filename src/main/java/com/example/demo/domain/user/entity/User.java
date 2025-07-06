package com.example.demo.domain.user.entity;

import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.community.entity.UserCommunity;
import com.example.demo.domain.notification.entity.Notification;
import com.example.demo.domain.post.entity.Post;
import com.example.demo.domain.post.entity.PostLike;
import com.example.demo.domain.problem.entity.UserProblem;
import com.example.demo.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    private String loginId;  // 로그인 ID
    private String password;
    private String username;
    private String nickname;

    private String profileImageUrl;


    private Boolean isDeleted;


    @Builder
    public User(String loginId, String password, String username, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.profileImageUrl = null;
        this.isDeleted = false;
    }


    public void updateProfile(String nickname, String imageUrl) {
        this.nickname = nickname;
        this.profileImageUrl = imageUrl;
    }

    public void deleteUser() {
        this.isDeleted = true;
    }

    @OneToMany(mappedBy = "user")
    private List<UserCommunity> userCommunities = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PostLike> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserProblem> userProblems = new ArrayList<>();

    public String getLoginId() {
        return loginId;
    }
}