package com.example.demo.domain.category.entity;

import com.example.demo.domain.exam.entity.Exam;
import com.example.demo.domain.post.entity.Post;
import com.example.demo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ExamCategory categoryType;

    @OneToMany(mappedBy = "category")
    private List<Exam> exams = new ArrayList<>();
}
