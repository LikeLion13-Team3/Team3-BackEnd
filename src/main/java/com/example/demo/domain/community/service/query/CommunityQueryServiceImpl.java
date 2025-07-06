package com.example.demo.domain.community.service.query;


import com.example.demo.domain.community.entity.UserCommunity;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityQueryServiceImpl implements CommunityQueryService {
    private final UserRepository userRepository;

    @Override
    public List<UserCommunity> getMyCommunities(String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
        return user.getUserCommunities();
    }
}
