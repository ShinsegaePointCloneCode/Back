package com.example.smilekarina.club.presentation;

import com.example.smilekarina.club.application.ClubService;
import com.example.smilekarina.club.domain.ClubType;
import com.example.smilekarina.club.vo.BizIn;
import com.example.smilekarina.club.vo.CarIn;
import com.example.smilekarina.club.vo.MomKidsIn;
import com.example.smilekarina.global.vo.ResponseOut;
import com.example.smilekarina.user.application.UserService;
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
    private final UserService userService;
    @PostMapping("/myinfo/clubAgree/momkids")
    public ResponseEntity<?> createClubMom(@RequestHeader("Authorization") String token, @RequestBody MomKidsIn momKidsIn) {
        clubService.registerClubForMemberKids(token, momKidsIn);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
    @PostMapping("/myinfo/clubAgree/beauty")
    public ResponseEntity<?> createClubBeauty(@RequestHeader("Authorization") String token) {
        clubService.registerClubForMemberBeauty(token);
        log.info("clubservice token {}",token);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
    @PostMapping("/myinfo/clubAgree/car")
    public ResponseEntity<?> createClubCar(@RequestHeader("Authorization") String token, @RequestBody CarIn carIn) {
        clubService.registerClubForCar(token, carIn);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
    @PostMapping("/myinfo/clubAgree/biz")
    public ResponseEntity<?> createClubBiz(@RequestHeader("Authorization") String token, @RequestBody BizIn bizIn) {
        System.out.println(token);
        clubService.registerClubForBiz(token, bizIn);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
    @DeleteMapping("/myinfo/clubAgree/{clubType}")
    public ResponseEntity<?> deleteClubMom(@RequestHeader("Authorization") String token, @PathVariable String clubType) {
        ClubType type = ClubType.fromString(clubType);
        clubService.clear(token, type);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
}
