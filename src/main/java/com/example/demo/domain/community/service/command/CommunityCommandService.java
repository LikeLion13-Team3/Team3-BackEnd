package com.example.demo.domain.community.service.command;

public interface CommunityCommandService {
    Long joinCommunity(Long examId, String loginId);
    void leaveCommunity(String loginId, Long communityId);

}
