package com.example.demo.domain.community.service.query;

import com.example.demo.domain.community.entity.UserCommunity;

import java.util.List;

public interface CommunityQueryService {
    List<UserCommunity> getMyCommunities(String loginId);
}
