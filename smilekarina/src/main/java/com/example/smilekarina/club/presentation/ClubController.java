package com.example.smilekarina.club.presentation;

import com.example.smilekarina.club.application.ClubService;
import com.example.smilekarina.club.domain.ClubType;
import com.example.smilekarina.club.vo.AllStateOut;
import com.example.smilekarina.club.vo.BizIn;
import com.example.smilekarina.club.vo.CarIn;
import com.example.smilekarina.club.vo.MomKidsIn;
import com.example.smilekarina.global.vo.ResponseOut;
import com.example.smilekarina.user.application.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary= "맘클럽 만들기", description= "token과 momclub 입력 넣으면", tags = { "Club Controller" })
    @PutMapping("/myinfo/clubAgree/momkids")
    public ResponseEntity<?> createClubMom(@RequestHeader("Authorization") String token, @RequestBody MomKidsIn momKidsIn) {
        clubService.registerClubForMomKids(token, momKidsIn);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
    @Operation(summary= "뷰티클럽 만들기", description= "token 넣으면", tags = { "Club Controller" })
    @PostMapping("/myinfo/clubAgree/beauty")
    public ResponseEntity<?> createClubBeauty(@RequestHeader("Authorization") String token) {
        clubService.registerClubForBeauty(token);
        log.info("clubservice token {}",token);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
    @Operation(summary= "카클럽 만들기", description= "token과 carclub 입력 넣으면", tags = { "Club Controller" })
    @PutMapping("/myinfo/clubAgree/car")
    public ResponseEntity<?> createClubCar(@RequestHeader("Authorization") String token, @RequestBody CarIn carIn) {
        clubService.registerClubForCar(token, carIn);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
    @Operation(summary= "비즈클럽 만들기", description= "token과 bizclub 입력 넣으면", tags = { "Club Controller" })
    @PutMapping("/myinfo/clubAgree/biz")
    public ResponseEntity<?> createClubBiz(@RequestHeader("Authorization") String token, @RequestBody BizIn bizIn) {
        clubService.registerClubForBiz(token, bizIn);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
    @Operation(summary= "클럽 찾기", description= "token에 맞는 유저의 클럽 내역 찾기", tags = { "Club Controller" })
    @GetMapping("/myinfo/clubAgree/{clubTypeDo}")
    public ResponseEntity<?> getClubMom(@RequestHeader("Authorization") String token,@PathVariable String clubTypeDo) {
        ClubType clubType = ClubType.valueOf(clubTypeDo.toUpperCase());
        Object data = null;
        switch (clubType) {
            case MOMKIDS -> data = clubService.getMomKidsData(token);
            case CAR -> data = clubService.getCarData(token);
            case BIZ -> data = clubService.getBizData(token);
            default -> throw new IllegalArgumentException("Unsupported club type");
        }
        ResponseOut<?> responseOut = ResponseOut.success(data);
        return ResponseEntity.ok(responseOut);
    }
    @Operation(summary= "클럽 상태 찾기", description= "token에 맞는 유저의 클럽 가입 내역 찾기", tags = { "Club Controller" })
    @GetMapping("/myinfo/clubAgree/allState")
    public ResponseEntity<?> getClubState(@RequestHeader("Authorization") String token) {
        AllStateOut allStateOut = clubService.getClubState(token);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
    @Operation(summary= "제거하기", description= "token과 클럽 타입넣으면", tags = { "Club Controller" })
    @DeleteMapping("/myinfo/clubAgree/{clubType}")
    public ResponseEntity<?> deleteClubMom(@RequestHeader("Authorization") String token, @PathVariable String clubType) {
        ClubType type = ClubType.fromString(clubType);
        clubService.clear(token, type);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
}
