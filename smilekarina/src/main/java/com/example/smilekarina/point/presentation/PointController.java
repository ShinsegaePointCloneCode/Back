package com.example.smilekarina.point.presentation;

import com.example.smilekarina.global.vo.ResponseOut;
import com.example.smilekarina.point.application.PointService;
import com.example.smilekarina.point.vo.*;
import com.example.smilekarina.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class PointController {

    private final PointService pointService;
    private final UserService userService;

    /*
        포인트 비밀번호 변경
     */
    @PutMapping("/user/pointpwdChg")
    public ResponseEntity<?> changePointPassword(@RequestHeader("Authorization") String token,
                                                 @RequestBody PointPasswordModifyIn pointPasswordModifyIn) {

        pointService.modifyPointPassword(token, pointPasswordModifyIn.getPointPassword());
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

    /*
        사용가능 포인트 조회
     */
    @GetMapping("/point/usablepoint")
    public ResponseEntity<?> getPointWidget(@RequestHeader("Authorization") String token) {

        Long userId = userService.getUserIdFromToken(token);

        Integer usablePoint = pointService.getUsablePoint(userId);

        UsablePointOut usablePointOut = UsablePointOut.builder()
                .totalPoint(usablePoint)
                .build();

        ResponseOut<?> responseOut = ResponseOut.success(usablePointOut);
        return ResponseEntity.ok(responseOut);
    }

    /*
        포인트 내역 상단
     */
    @GetMapping("/point/pointinfo")
    public ResponseEntity<?> getPointInfo(@RequestHeader("Authorization") String token) {

        Long userId = userService.getUserIdFromToken(token);
        PointInfoOut pointInfoOut = pointService.getPointInfo(userId);
        ResponseOut<?> responseOut = ResponseOut.success(pointInfoOut);
        return ResponseEntity.ok(responseOut);
    }

    /*
        포인트 리스트 조회
     */









//    /*
//        테스트 데이터 넣기용
//     */
//    @PostMapping("/point")
//    void addPoint(@RequestBody PointIn pointIn) {
//        log.info("INPUT Object Data is : {}" , pointIn);
//        PointAddDto pointAddDto = PointAddDto.builder()
//                .pointType(pointIn.getPointType())
//                .point(pointIn.getPoint())
//                .used(pointIn.getUsed())
//                .userId(pointIn.getUserId())
//                .build();
//        pointService.registerPoint(pointAddDto);
//    }
}