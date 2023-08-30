package com.example.smilekarina.card.application;

import com.example.smilekarina.card.domain.IssueType;
import com.example.smilekarina.card.domain.IssueTypeConverter;
import com.example.smilekarina.card.domain.MileageCard;
import com.example.smilekarina.card.domain.PointCard;
import com.example.smilekarina.card.dto.AffiliateCardDto;
import com.example.smilekarina.card.dto.PointCardDto;
import com.example.smilekarina.card.infrastructure.MileageCardRepository;
import com.example.smilekarina.card.infrastructure.PointCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardService{

    private final PointCardRepository pointCardRepository;
    private final MileageCardRepository mileageCardRepository;

    private static final String SAMSUNG = "삼성전자 포인트";

    // 신규 포인트 카드 등록
    @Override
    public void registerPointCard(PointCardDto pointCardDto) {
        createPointCard(pointCardDto);
    }

    // 제휴 멤버십 카드 등록
    @Override
    public void registerAffiliateCard(AffiliateCardDto affiliateCardDto) {

        // 삼성전자 포인트인 경우에는 포인트카드 테이블에 등록
        if(affiliateCardDto.getIssueStore().equals(SAMSUNG)) {

            PointCardDto pointCardDto = PointCardDto.builder()
                    .userId(affiliateCardDto.getUserId())
                    .cardNumber(affiliateCardDto.getCardNumber())
                    .issueType(IssueType.PARTNERSHIP.getCode())
                    .issueStore(affiliateCardDto.getIssueStore())
                    .build();
            createPointCard(pointCardDto);
        } else {
            // 아시아나, 대한항공인 경우에는 마일리지카드 테이블에 등록

            // 대표카드선택유무 판단하기
            List<MileageCard> mileageCardList = mileageCardRepository.findByUserId(affiliateCardDto.getUserId());

            boolean mainSelect = false;

            // 기존에 등록된 제휴 마일리지카드가 없는 경우에는 true로 설정
            if(mileageCardList.isEmpty()) {
                mainSelect = true;
            }

            MileageCard mileageCard = MileageCard.builder()
                    .cardNumber(affiliateCardDto.getCardNumber())
                    .userId(affiliateCardDto.getUserId())
                    .issuePlace(affiliateCardDto.getIssueStore())
                    .lastName(affiliateCardDto.getLastName())
                    .mainSelect(mainSelect)
                    .build();
            mileageCardRepository.save(mileageCard);
        }
    }


    // 포인트 카드 등록 처리
    private void createPointCard(PointCardDto pointCardDto) {

        IssueType issueType = new IssueTypeConverter().convertToEntityAttribute(pointCardDto.getIssueType());

        PointCard pointCard = PointCard.builder()
                .cardNumber(pointCardDto.getCardNumber())
                .userId(pointCardDto.getUserId())
                .issueType(issueType)
                .issuePlace(pointCardDto.getIssueStore())
                .build();

        pointCardRepository.save(pointCard);
    }





}
