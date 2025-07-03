package com.example.demo.domain.exam.entity;

import com.example.demo.domain.category.entity.Category;
import com.example.demo.domain.category.entity.ExamCategory;
import com.example.demo.domain.community.entity.Community;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exam_name")
    private String examName;  // 자격증 이름

    @Column(name = "description")
    private String description;  // 자격증 설명

    @OneToOne(mappedBy = "exam")
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
