package com.example.smilekarina.club.presentation;

import com.example.smilekarina.club.application.ClubService;
import com.example.smilekarina.global.vo.ResponseOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class ClubController {
    private final ClubService clubService;
//
//    @PostMapping("/membership/club/create")
//    public ResponseEntity<?> createClub(@RequestHeader String token, @RequestBody String club_type, String club_text) {
//        // todo: 헤더에 token to user_id
//        clubService.createClub();
//        ResponseOut<?> responseOut = ResponseOut.success();
//        return ResponseEntity.ok(responseOut);
//    }


}
