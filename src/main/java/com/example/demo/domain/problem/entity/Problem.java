package com.example.demo.domain.problem.entity;

import com.example.demo.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "problem")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;  // 문제 내용

    @Column(name = "solution", nullable = false, columnDefinition = "TEXT")
    private String solution;  // 문제 해설

    @Column(name = "options", nullable = false, columnDefinition = "TEXT")
    private String options;  // 문제의 보기


    @Column(name = "correct_answer", nullable = false)
    private String correctAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @OneToMany(mappedBy = "problem")
    private List<UserProblem> userProblems = new ArrayList<>();
}
