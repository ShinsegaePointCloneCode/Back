package com.example.smilekarina.point.presentation;

import com.example.smilekarina.gift.application.GiftService;
import com.example.smilekarina.global.vo.ResponseOut;
import com.example.smilekarina.point.application.PointService;
import com.example.smilekarina.point.domain.PointType;
import com.example.smilekarina.point.dto.PointSearchConditionDto;
import com.example.smilekarina.point.vo.*;
import com.example.smilekarina.receipt.application.ReceiptService;
import com.example.smilekarina.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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
    private final ReceiptService receiptService;
    private final GiftService giftService;


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
    @GetMapping("/point/pointList")
    public ResponseEntity<?> getPointList(@RequestHeader("Authorization") String token,
                                          @RequestParam(value="pointType") String pointType,
                                          @RequestParam(value="rangeStartDate") String rangeStartDate,
                                          @RequestParam(value="rangeEndDate") String rangeEndDate,
                                          @RequestParam(value="usedType") String usedType,
                                          @RequestParam(value="pointHistoryType") String pointHistoryType,
                                          Pageable pageable) {

        PointSearchConditionDto pointSearchConditionDto = PointSearchConditionDto.builder()
                .token(token)
                .pointType(pointType)
                .rangeStartDate(rangeStartDate)
                .rangeEndDate(rangeEndDate)
                .usedType(usedType)
                .pointHistoryType(pointHistoryType)
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .offset(pageable.getOffset())
                .build();

        PointListOut pointListOut = pointService.getPointList(pointSearchConditionDto);
        ResponseOut<?> responseOut = ResponseOut.success(pointListOut);
        return ResponseEntity.ok(responseOut);
    }

    /*
        포인트 리스트 상세 내역 조회
     */
    @GetMapping("point/pointListDetail")
    public ResponseEntity<?> getPointList(@RequestHeader("Authorization") String token,
                                          @RequestParam(value="pointId") Long pointId,
                                          @RequestParam(value="pointType") String pointType) {

        PointContentOut pointContentOut = new PointContentOut();

        // 포인트 타입에 따라 각 서비스 호출, 순환참조 때문에 컨트롤러 단에서 각 서비스 호출
        // 단순 조회이기 때문에 트랜잭션은 문제 없음

        if(pointType.equals(PointType.SMARTRECEIPT.getValue())) {
            // 포인트 타입이 [스마트영수증]인 경우
            pointContentOut = receiptService.getSmartReceipt(pointId);

        } else if(pointType.equals(PointType.GENERAL.getValue())) {
            // [일반]인 경우
            pointContentOut = receiptService.getReceiptAccept(pointId);

        } else if(pointType.equals(PointType.EVENT.getValue()) ||
                pointType.equals(PointType.ROULETTE.getValue()) ||
                pointType.equals(PointType.CHECK.getValue())) {
            // 이벤트 당첨 적립은 어떻게 쌓이는지 정보가 없어서 일단은 룰렛, 출석 적립만 표시
            // [출석],[룰렛]인 경우
            pointContentOut = pointService.getEventPoint(pointType);

        } else if(pointType.equals(PointType.GIFT.getValue()) ||
                pointType.equals(PointType.CANCELGIFT.getValue())){
            // [선물], [선물사용취소]인 경우
            pointContentOut = giftService.getGiftPont(pointId, pointType);

        } else if(pointType.equals(PointType.EXTINCTION.getValue())) {
            // [소멸]인 경우
            pointContentOut = pointService.getExtinction();

        } else if(pointType.equals(PointType.CONVERT.getValue())) {
            // [전환]인 경우
        }

        ResponseOut<?> responseOut = ResponseOut.success(pointContentOut);
        return ResponseEntity.ok(responseOut);
    }

}