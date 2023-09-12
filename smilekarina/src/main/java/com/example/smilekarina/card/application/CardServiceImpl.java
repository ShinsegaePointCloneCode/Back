package com.example.smilekarina.card.application;

import com.example.smilekarina.card.domain.*;
import com.example.smilekarina.card.dto.AffiliateCardDto;
import com.example.smilekarina.card.dto.PointCardDto;
import com.example.smilekarina.card.infrastructure.CreditCardRepository;
import com.example.smilekarina.card.infrastructure.MileageCardRepository;
import com.example.smilekarina.card.infrastructure.PointCardRepository;
import com.example.smilekarina.card.vo.CreditCardOut;
import com.example.smilekarina.card.vo.OfflinePointCardOut;
import com.example.smilekarina.card.vo.OnlinePointCardOut;
import com.example.smilekarina.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CardServiceImpl implements CardService{

    private final PointCardRepository pointCardRepository;
    private final CreditCardRepository creditCardRepository;
    private final MileageCardRepository mileageCardRepository;
    private final UserService userService;

    private static final String SAMSUNG = "삼성전자 포인트";
    private static final String SHINSEGAEPOINTDOTCOM = "신세계포인트닷컴";

    // 신규 포인트 카드 등록
    @Override
    @Transactional(readOnly = false)
    public void registerPointCard(PointCardDto pointCardDto) {
        createPointCard(pointCardDto);
    }

    // 제휴 멤버십 카드 등록
    @Override
    @Transactional(readOnly = false)
    public void registerAffiliateCard(AffiliateCardDto affiliateCardDto) {

        // 삼성전자 포인트인 경우에는 포인트카드 테이블에 등록
        if(affiliateCardDto.getIssueStore().equals(SAMSUNG)) {

            PointCardDto pointCardDto = PointCardDto.builder()
                    .token(affiliateCardDto.getToken())
                    .cardNumber(affiliateCardDto.getCardNumber())
                    .issueType(IssueType.PARTNERSHIP.getCode())
                    .issueStore(affiliateCardDto.getIssueStore())
                    .build();
            createPointCard(pointCardDto);
        } else {
            // 아시아나, 대한항공인 경우에는 마일리지카드 테이블에 등록

            Long userId = userService.getUserIdFromToken(affiliateCardDto.getToken());

            // 대표카드선택유무 판단하기
            List<MileageCard> mileageCardList = mileageCardRepository.findByUserId(userId);

            boolean mainSelect = false;

            // 기존에 등록된 제휴 마일리지카드가 없는 경우에는 true로 설정
            if(mileageCardList.isEmpty()) {
                mainSelect = true;
            }

            MileageCard mileageCard = MileageCard.builder()
                    .cardNumber(affiliateCardDto.getCardNumber())
                    .userId(userId)
                    .issuePlace(affiliateCardDto.getIssueStore())
                    .lastName(affiliateCardDto.getLastName())
                    .mainSelect(mainSelect)
                    .build();
            mileageCardRepository.save(mileageCard);
        }
    }

    // 온라인 카드 조회
    @Override
    public List<OnlinePointCardOut> getOnlinePointCardList(String token) {

        Long userId = userService.getUserIdFromToken(token);

        IssueType issueType = new IssueTypeConverter().convertToEntityAttribute(IssueType.ONLINE.getCode());

        List<PointCard> onlinePointCardList = pointCardRepository.findByUserIdAndIssueType(userId, issueType);

        List<OnlinePointCardOut> dtoList = new ArrayList<>();
        for(PointCard card : onlinePointCardList) {
            OnlinePointCardOut out = OnlinePointCardOut.builder()
                    .cardNumber(card.getCardNumber())
                    .issuePlace(card.getIssuePlace())
                    .createdDate(card.getCreatedDate())
                    .build();
            dtoList.add(out);
        }

        return dtoList;
    }

    // 제휴 신용카드 조회
    @Override
    public List<CreditCardOut> getCreditCardList(String token) {

        Long userId = userService.getUserIdFromToken(token);

        List<CreditCard> creditCardList = creditCardRepository.findByUserId(userId);

        if(creditCardList.isEmpty()) {
            return null;
        }

        List<CreditCardOut> creditCardOutList = new ArrayList<>();
        for(CreditCard card : creditCardList) {
            CreditCardOut out = CreditCardOut.builder()
                    .cardName(card.getCardName())
                    .cardNumber(card.getCardNumber())
                    .issuePlace(card.getIssuePlace())
                    .createdDate(card.getCreatedDate())
                    .build();

            creditCardOutList.add(out);
        }

        return creditCardOutList;
    }

    // 오프라인 카드 조회
    @Override
    public List<OfflinePointCardOut> getOfflinePointCardList(String token) {

        Long userId = userService.getUserIdFromToken(token);

        IssueType issueType = new IssueTypeConverter().convertToEntityAttribute(IssueType.OFFLINE.getCode());

        List<PointCard> offlinePointCardList = pointCardRepository.findByUserIdAndIssueType(userId, issueType);

        if(offlinePointCardList.isEmpty()) {
            return null;
        }

        List<OfflinePointCardOut> dtoList = new ArrayList<>();
        for(PointCard card : offlinePointCardList) {
            OfflinePointCardOut out = OfflinePointCardOut.builder()
                    .cardNumber(card.getCardNumber())
                    .issuePlace(card.getIssuePlace())
                    .createdDate(card.getCreatedDate())
                    .build();
            dtoList.add(out);
        }

        return dtoList;
    }

    // 포인트카드 번호 조회(바코드 보기 위함)
    @Override
    public String getPointCardNumber(String token) {

        Long userId = userService.getUserIdFromToken(token);

        IssueType issueType = new IssueTypeConverter().convertToEntityAttribute(IssueType.ONLINE.getCode());

        PointCard pointCard = pointCardRepository.findFirstByUserIdAndIssueTypeAndIssuePlaceOrderByCreatedDateDesc
                (userId, issueType, SHINSEGAEPOINTDOTCOM);

        return pointCard.getCardNumber();
    }

    // 회원가입시 온라인포인트카드 등록 - 회원가입 후 화면로딩시 에러 발생 예방을 위해 임시로 만들어 둠
    @Override
    @Transactional(readOnly = false)
    public void registerOnlinePointCard(Long userId) {

        // 고정 문자열 + 유저Id 값
        String cardNumber = "9350120011212405";
        Long onlineCardNumber = Long.parseLong(cardNumber) + userId;

        PointCard pointCard = PointCard.builder()
                .cardNumber(Long.toString(onlineCardNumber))
                .userId(userId)
                .issueType(IssueType.ONLINE)
                .issuePlace(SHINSEGAEPOINTDOTCOM)
                .build();

        pointCardRepository.save(pointCard);
    }

    // 포인트 카드 등록 처리 - 원래 private였는데 Transactional 쓰기 위해 public으로 선언
    @Transactional(readOnly = false)
    public void createPointCard(PointCardDto pointCardDto) {

        Long userId = userService.getUserIdFromToken(pointCardDto.getToken());

        IssueType issueType = new IssueTypeConverter().convertToEntityAttribute(pointCardDto.getIssueType());

        PointCard pointCard = PointCard.builder()
                .cardNumber(pointCardDto.getCardNumber())
                .userId(userId)
                .issueType(issueType)
                .issuePlace(pointCardDto.getIssueStore())
                .build();

        pointCardRepository.save(pointCard);
    }

}
