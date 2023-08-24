package com.example.smilekarina.gift.application;

import com.example.smilekarina.gift.domain.Gift;
import com.example.smilekarina.gift.domain.GiftType;
import com.example.smilekarina.gift.domain.GiftTypeConverter;
import com.example.smilekarina.gift.infrastructure.GiftRepository;
import com.example.smilekarina.gift.vo.GiftIn;
import com.example.smilekarina.point.application.PointService;
import com.example.smilekarina.point.domain.PointType;
import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class GiftServiceImpl implements GiftService {

    private final UserService userService;
    private final PointService pointService;
    private final GiftRepository giftRepository;

    // 포인트 선물하기
    @Override
    public void registerGift(Long userId, GiftIn giftIn) {

        // 받는사람 로그인 아이디로 유저 아이디 추출
        Long recipientId = userService.getUserId(giftIn.getRecipientLoginId());

        // 보낸사람 포인트 테이블에 선물, 사용으로 데이터 추가
        PointAddDto pointAddDto = PointAddDto.builder()
                .point(giftIn.getPoint())
                .pointType(PointType.GIFT.getCode())
                .used(true)
                .userId(userId)
                .build();
       Long senderPointId = pointService.registerPoint(pointAddDto);

        // 선물 테이블에 데이터 추가
        GiftType giftType = new GiftTypeConverter().convertToEntityAttribute(GiftType.WAIT.getCode());

        Gift gift = Gift.builder()
                .giftRecipientId(recipientId)
                .giftMessage(giftIn.getGiftMessage())
                .imageId(giftIn.getImageId())
                .giftSenderId(userId)
                .giftType(giftType)
                .senderPointId(senderPointId)
                .build();
        giftRepository.save(gift);
    }
}
