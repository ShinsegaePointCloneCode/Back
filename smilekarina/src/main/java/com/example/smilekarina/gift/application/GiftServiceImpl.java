package com.example.smilekarina.gift.application;

import com.example.smilekarina.gift.domain.Gift;
import com.example.smilekarina.gift.domain.GiftType;
import com.example.smilekarina.gift.domain.GiftTypeConverter;
import com.example.smilekarina.gift.dto.GiftAcceptDto;
import com.example.smilekarina.gift.dto.GiftCancelDto;
import com.example.smilekarina.gift.dto.GiftLastDto;
import com.example.smilekarina.gift.infrastructure.GiftRepository;
import com.example.smilekarina.gift.vo.GiftIn;
import com.example.smilekarina.point.application.PointService;
import com.example.smilekarina.point.domain.PointType;
import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.user.application.UserService;
import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
//@Transactional(readOnly = true)
public class GiftServiceImpl implements GiftService {

    private final UserService userService;
    private final PointService pointService;
    private final GiftRepository giftRepository;
    private final UserRepository userRepository;

    // 포인트 선물하기
    @Override
    //    @Transactional(readOnly = false)
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
                .point(giftIn.getPoint())
                .senderPointId(senderPointId)
                .build();
        giftRepository.save(gift);
    }

    // 포인트 선물 받기 내역 조회(가장최근 것만)
    @Override
    public GiftLastDto getLastGift(String token) {

        // 토큰 정보에서 userId 값 가져 오기
        Long userId = userService.getUserIdFromToken(token);

        // 선물 테이블에서 대기 중인 가장 최근 선물 정보만 가져오기
        GiftType giftType = new GiftTypeConverter().convertToEntityAttribute(GiftType.WAIT.getCode());
        Optional<Gift> gift= giftRepository.findFirstByGiftRecipientIdAndGiftTypeOrderByIdDesc(userId, giftType);

        // 대기중인 선물 정보가 없는 경우 null을 리턴
        if(gift.isEmpty()) {
            return null;
        }

        Gift targetGift = gift.get();

        // 보낸 유저 정보 가져오기  TODO 데이터 가지러 테이블 여러번 왔다 갔다 해도 될지 고민
        User user = userRepository.findById(targetGift.getGiftSenderId()).orElseThrow(() -> new NoSuchElementException("User not found"));

        return GiftLastDto.builder()
                .giftId(targetGift.getId())
                .senderLoginId(user.getLoginId())
                .senderName(user.getName())
                .point(targetGift.getPoint())
                .giftMessage(targetGift.getGiftMessage())
                .createdDate(targetGift.getCreatedDate())
                .build();
    }

    // 포인트 선물 수락
    @Override
    //    @Transactional(readOnly = false)
    @Transactional
    public void acceptGift(GiftAcceptDto giftAcceptDto) {

        Optional<Gift> gift = giftRepository.findById(giftAcceptDto.getGiftId());

        gift.ifPresent(modifiedGift -> {

            // 포인트 테이블에 받는 사람의 적립 포인트 데이터 추가
            PointAddDto pointAddDto = PointAddDto.builder()
                    .point(modifiedGift.getPoint())
                    .pointType(PointType.GIFT.getCode())
                    .used(false)
                    .userId(giftAcceptDto.getUserId())
                    .build();
            Long pointId = pointService.registerPoint(pointAddDto);

            // 선물 테이블의 선물 타입을 받음으로 변경하고, 결과 포인트ID에 받는사람 포인트id를 등록
            GiftType giftType = new GiftTypeConverter().convertToEntityAttribute(GiftType.GET.getCode());
            modifiedGift.setGiftType(giftType);
            modifiedGift.setResultPointId(pointId);
        });
    }

    // 포인트 선물 거절
    @Override
    //    @Transactional(readOnly = false)
    @Transactional
    public void cancelGift(GiftCancelDto giftCancelDto) {

        Optional<Gift> gift = giftRepository.findById(giftCancelDto.getGiftId());

        gift.ifPresent(modifiedGift -> {

            // 포인트 테이블에 보낸 사람의 선물사용취소(적립) 포인트 데이터 추가
            PointAddDto pointAddDto = PointAddDto.builder()
                    .point(modifiedGift.getPoint())
                    .pointType(PointType.CANCELGIFT.getCode())
                    .used(false)
                    .userId(modifiedGift.getGiftSenderId())
                    .build();
            Long pointId = pointService.registerPoint(pointAddDto);

            // 선물 테이블의 선물 타입을 취소로 변경하고, 결과 포인트ID에 보낸사람의 포인트 ID를 등록
            GiftType giftType = new GiftTypeConverter().convertToEntityAttribute(GiftType.CANCEL.getCode());
            modifiedGift.setGiftType(giftType);
            modifiedGift.setResultPointId(pointId);
        });
    }

}
