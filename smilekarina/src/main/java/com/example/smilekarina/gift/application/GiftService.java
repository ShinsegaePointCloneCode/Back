package com.example.smilekarina.gift.application;

import com.example.smilekarina.gift.dto.GiftAcceptDto;
import com.example.smilekarina.gift.dto.GiftCancelDto;
import com.example.smilekarina.gift.dto.GiftLastDto;
import com.example.smilekarina.gift.dto.GiftSearchConditionDto;
import com.example.smilekarina.gift.vo.GiftIn;
import com.example.smilekarina.gift.vo.GiftListOut;
import com.example.smilekarina.gift.vo.GiftMessageOut;
import com.example.smilekarina.gift.vo.GiftPointDetailOut;
import com.example.smilekarina.point.vo.PointContentOut;

public interface GiftService {

    // 포인트 선물하기
    void registerGift(String token, GiftIn giftIn);

    // 포인트 선물 받기 내역 조회(가장최근 것만)
    GiftLastDto getLastGift(String token);

    // 포인트 선물 수락
    void acceptGift(GiftAcceptDto giftAcceptDto);

    // 포인트 선물 거절
    void cancelGift(GiftCancelDto giftCancelDto);

    // 포인트 선물하기 하단 리스트 조회
    GiftListOut getPointGiftList(GiftSearchConditionDto giftSearchConditionDto);

    // 포인트 선물하기 하단 리스트 상세조회
    GiftPointDetailOut getGiftPointDetail(Long pointId);

    // 선물 메세지 조회
    GiftMessageOut getGiftMessage(Long giftId);

    // 포인트 리스트 상세 내역 조회 - 선물, 선물사용취소
    PointContentOut getGiftPont(Long pointId, String pointType);
}
