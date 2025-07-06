package com.example.demo.domain.mission.controller;


import com.example.demo.domain.mission.service.query.MissionQueryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {
    private final MissionQueryService missionQueryService;

    @Operation(description = "미션 페이지 정보 조회")
    @GetMapping("/communities/{communityId}/missions")
    public ResponseEntity<?> getMissionPage(@PathVariable Long communityId) {
        return ResponseEntity.ok(missionQueryService.getMissionPage(communityId));
    }
}
