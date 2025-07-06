package com.example.demo.domain.mission.repository;

import com.example.demo.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Optional<Mission> findByCommunityId(Long communityId);
}
