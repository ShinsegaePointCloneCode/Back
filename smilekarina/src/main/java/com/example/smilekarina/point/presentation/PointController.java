package com.example.smilekarina.point.presentation;

import com.example.smilekarina.global.vo.ResponseOut;
import com.example.smilekarina.point.application.PointService;
import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.point.dto.PointGetDto;
import com.example.smilekarina.point.vo.PointIn;
import com.example.smilekarina.point.vo.PointOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class
PointController {

    private final PointService pointService;

//    /*
//        헤더 포인트 조회
//     */
//    @GetMapping("/point/widget/{UUID}")
//    public ResponseEntity<?> getPointWidget(@PathVariable String UUID) {
//
//        // TODO 일단 uuid 받는다고 가정하고 구현함. 나중에 토큰에서 받은값으로 유저정보 얻는 처리로 수정예정
////        String uuid = "f5f3737a-fabb-4492-a27c-4262bb1c2d51";
//
//        // TODO 적립예정 포인트는 사용가능포인트에서 제외 해야함
//
//        Integer usablePoint = pointService.getUsablePoint(UUID);
//        ResponseOut<?> responseOut = ResponseOut.success(usablePoint);
//        return ResponseEntity.ok(responseOut);
//    }
//
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