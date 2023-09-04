package com.example.smilekarina.point.presentation;

import com.example.smilekarina.global.vo.ResponseOut;
import com.example.smilekarina.point.application.PointService;
import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.point.dto.PointGetDto;
import com.example.smilekarina.point.vo.PointIn;
import com.example.smilekarina.point.vo.PointOut;
import com.example.smilekarina.point.vo.PointPasswordModifyIn;
import com.example.smilekarina.point.vo.UsablePointOut;
import com.example.smilekarina.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class PointController {

    private final PointService pointService;
    private final UserService userService;

    /*
        포인트 비밀번호 변경
     */
    @PatchMapping("/user/pointpwdChg")
    public ResponseEntity<?> changePointPassword(@RequestHeader("Authorization") String token,
                                                 @RequestBody PointPasswordModifyIn pointPasswordModifyIn) {

        pointService.modifyPointPassword(token, pointPasswordModifyIn.getPointPassword());
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

    /*
        포인트 내역 상단
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






    // 밑에는 참고 코드(강사님 코드)*****************************************

//    @PostMapping("/point")
//    void addPoint(@RequestBody PointIn pointIn) {
//        log.info("INPUT Object Data is : {}" , pointIn);
//        PointAddDto pointAddDto = PointAddDto.builder()
//                .pointType(pointIn.getPointType())
//                .point(pointIn.getPoint())
//                .used(pointIn.getUsed())
//                .loginId(pointIn.getLoginId())
//                .build();
//        pointService.createPoint(pointAddDto);
//    }
//
//    @GetMapping("/point/{userId}")
//    public List<PointOut> getPointByUser(@PathVariable Long userId) {
//        log.info("INPUT userId is : {}" , userId);
//        List<PointGetDto> pointListByUser = pointService.getPointByUser(userId);
//        List<PointOut> pointOutList = pointListByUser.stream().map(pointGetDto -> {
//            return PointOut.builder()
//                    .pointType(pointGetDto.getPointType())
//                    .point(pointGetDto.getPoint())
//                    .used(pointGetDto.getUsed())
//                    .build();
//        }).toList();
//        log.info("OUTPUT pointOutList is : {}" , pointOutList);
//        return pointOutList;
//    }
}