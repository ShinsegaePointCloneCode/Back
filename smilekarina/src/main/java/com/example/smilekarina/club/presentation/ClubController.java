package com.example.smilekarina.club.presentation;

import com.example.smilekarina.club.application.ClubService;
import com.example.smilekarina.global.vo.ResponseOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class ClubController {
    private final ClubService clubService;

    @PostMapping("/membership/club/create")
    public ResponseEntity<?> createClubList(@RequestBody String UUID) {
        // todo: 헤더에 UUID 들어가면 뽑아오는 로직
        clubService.createClubList(UUID);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }


}
