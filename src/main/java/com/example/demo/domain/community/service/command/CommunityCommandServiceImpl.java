package com.example.demo.domain.community.service.command;

import com.example.demo.domain.community.entity.Community;
import com.example.demo.domain.community.entity.UserCommunity;
import com.example.demo.domain.community.repository.CommunityRepository;
import com.example.demo.domain.community.repository.UserCommunityRepository;
import com.example.demo.domain.exam.entity.Exam;
import com.example.demo.domain.exam.repository.ExamRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityCommandServiceImpl implements CommunityCommandService {

    private final ExamRepository examRepository;
    private final CommunityRepository communityRepository;
    private final UserCommunityRepository userCommunityRepository;
    private final UserRepository userRepository;

    @Override
    public Long joinCommunity(Long examId, String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        int joinedCount = userCommunityRepository.countByUserId(user.getId());
        if (joinedCount >= 3) { // 3개까지 허용
            throw new IllegalStateException("최대 3개의 커뮤니티에만 참여할 수 있습니다.");
        }

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new IllegalArgumentException("해당 시험이 존재하지 않습니다."));

        Community community = communityRepository.findByExam(exam)
                .orElseThrow(() -> new IllegalArgumentException("해당 시험의 커뮤니티가 존재하지 않습니다."));

        if (userCommunityRepository.existsByUserAndCommunity(user, community)) {
            throw new IllegalStateException("이미 이 커뮤니티에 참여했습니다.");
        }

        UserCommunity userCommunity = UserCommunity.builder()
                .user(user)
                .community(community)
                .build();

        userCommunityRepository.save(userCommunity);


        return userCommunity.getUserCommunityId();
    }

    @Override
    public void leaveCommunity(String loginId, Long communityId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        UserCommunity userCommunity = userCommunityRepository.findByUserIdAndCommunityId(user.getId(), communityId)
                .orElseThrow(() -> new IllegalStateException("참여한 커뮤니티를 찾을 수 없습니다."));

        userCommunityRepository.delete(userCommunity);
    }
}
