package com.example.smilekarina.gift.application;

import com.example.smilekarina.gift.vo.GiftIn;

public interface GiftService {

    // 포인트 선물하기
    void registerGift(Long userId, GiftIn giftIn);

}
