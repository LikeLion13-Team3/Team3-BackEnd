package com.example.demo.domain.community.service;

import com.example.demo.domain.community.entity.Community;
import com.example.demo.domain.community.repository.CommunityRepository;
import com.example.demo.domain.exam.entity.Exam;
import com.example.demo.domain.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommunityInitializer implements CommandLineRunner {

    private final ExamRepository examRepository;
    private final CommunityRepository communityRepository;

    @Override
    public void run(String... args) {
        List<Exam> exams = examRepository.findAll();

        for (Exam exam : exams) {
            if (!communityRepository.existsByExam(exam)) {
                Community community = Community.builder()
                        .exam(exam)
                        .communityName(exam.getExamName() + " 커뮤니티")
                        .build();

                communityRepository.save(community);
                log.info("[커뮤니티 생성] " + community.getCommunityName());
            }
        }
    }
}