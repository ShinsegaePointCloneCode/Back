package com.example.smilekarina.gift.presentation;

import com.example.smilekarina.gift.application.GiftService;
import com.example.smilekarina.gift.dto.GiftAcceptDto;
import com.example.smilekarina.gift.dto.GiftCancelDto;
import com.example.smilekarina.gift.dto.GiftLastDto;
import com.example.smilekarina.gift.dto.GiftSearchConditionDto;
import com.example.smilekarina.gift.vo.*;
import com.example.smilekarina.global.vo.ResponseOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class GiftController {

    private final ModelMapper modelMapper;
    private final GiftService giftService;

    /*
        포인트 선물하기
     */
    @PostMapping("/point/gift")
    public ResponseEntity<?> createGift(@RequestHeader("Authorization") String token, @RequestBody GiftIn giftIn) {

        // 선물하기 처리
        giftService.registerGift(token, giftIn);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

    /*
        포인트 선물 받기 내역 조회(가장 최근 것만)
     */
    @GetMapping("/gift/getlast")
    public ResponseEntity<ResponseOut<?>> getLastGift(@RequestHeader("Authorization") String token) {

        GiftLastDto giftLastDto = giftService.getLastGift(token);

        if(giftLastDto == null) {
            ResponseOut<?> responseOut = ResponseOut.success();
            return ResponseEntity.ok(responseOut);
        } else {
            ResponseOut<?> responseOut = ResponseOut.success(modelMapper.map(giftLastDto, GiftLastOut.class));
            return ResponseEntity.ok(responseOut);
        }
    }

    /*
        포인트 선물 수락
     */
    @PostMapping("/gift/accept")
    public ResponseEntity<?> acceptGift(@RequestHeader("Authorization") String token, @RequestBody GiftAcceptIn giftAcceptIn) {

        GiftAcceptDto giftAcceptDto = GiftAcceptDto.builder()
                .giftId(giftAcceptIn.getGiftId())
                .token(token)
                .build();

        giftService.acceptGift(giftAcceptDto);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

    /*
        포인트 선물 거절
     */
    @PostMapping("/gift/cancel")
    public ResponseEntity<?> cancelGift(@RequestBody GiftCancelIn giftCancelIn) {

        GiftCancelDto giftCancelDto = GiftCancelDto.builder()
                .giftId(giftCancelIn.getGiftId())
                .build();

        giftService.cancelGift(giftCancelDto);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

    /*
        포인트 선물 리스트 조회
     */
    @GetMapping("/point/pointGiftList")
    public ResponseEntity<?> getPointGiftList(@RequestHeader("Authorization") String token,
                                              @RequestParam(value="giftGb") String giftGb,
                                              Pageable pageable) {

        GiftSearchConditionDto giftSearchConditionDto = GiftSearchConditionDto.builder()
                .token(token)
                .giftGb(giftGb)
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .offset(pageable.getOffset())
                .build();

        GiftListOut giftListOut = giftService.getPointGiftList(giftSearchConditionDto);
        ResponseOut<?> responseOut = ResponseOut.success(giftListOut);
        return ResponseEntity.ok(responseOut);
    }

    /*
        포인트 선물하기 하단 리스트 상세조회
     */
    @GetMapping("/point/pointGiftListDetail")
    public ResponseEntity<?> getPointGiftListDetail(@RequestHeader("Authorization") String token,
                                                    @RequestParam(value="pointId") Long pointId) {

        GiftPointDetailOut giftPointDetailOut = giftService.getGiftPointDetail(pointId);
        ResponseOut<?> responseOut = ResponseOut.success(giftPointDetailOut);
        return ResponseEntity.ok(responseOut);
    }

    /*
        선물 메시지 조회
     */
    @GetMapping("/gift/content")
    public ResponseEntity<?> getGiftMessage(@RequestHeader("Authorization") String token,
                                            @RequestParam(value="giftId") Long giftId) {

        GiftMessageOut getGiftMessage = giftService.getGiftMessage(giftId);
        ResponseOut<?> responseOut = ResponseOut.success(getGiftMessage);
        return ResponseEntity.ok(responseOut);
    }

}
