package com.example.smilekarina.card.application;

import com.example.smilekarina.card.dto.AffiliateCardDto;
import com.example.smilekarina.card.dto.PointCardDto;

public interface CardService {

    // 신규 포인트 카드 등록
    void registerPointCard(PointCardDto pointCardDto);


    // 제휴 멤버십 카드 등록
    void registerAffiliateCard(AffiliateCardDto affiliateCardDto);

}
