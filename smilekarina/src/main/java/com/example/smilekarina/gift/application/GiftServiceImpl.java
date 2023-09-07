package com.example.smilekarina.gift.application;

import com.example.smilekarina.gift.domain.Gift;
import com.example.smilekarina.gift.domain.GiftType;
import com.example.smilekarina.gift.domain.GiftTypeConverter;
import com.example.smilekarina.gift.domain.QGift;
import com.example.smilekarina.gift.dto.*;
import com.example.smilekarina.gift.infrastructure.GiftRepository;
import com.example.smilekarina.gift.vo.*;
import com.example.smilekarina.point.application.PointService;
import com.example.smilekarina.point.domain.Point;
import com.example.smilekarina.point.domain.PointType;
import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.point.infrastructure.PointRepository;
import com.example.smilekarina.user.application.UserService;
import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.infrastructure.UserRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class GiftServiceImpl implements GiftService {

    private final UserService userService;
    private final PointService pointService;
    private final GiftRepository giftRepository;
    private final UserRepository userRepository;
    private final PointRepository pointRepository;
    private final JPAQueryFactory query;

    // 포인트 선물하기
    @Override
    @Transactional(readOnly = false)
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
                .giftImage(giftIn.getGiftImage())
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

        // 보낸 유저 정보 가져오기
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
    @Transactional(readOnly = false)
    @jakarta.transaction.Transactional
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
    @Transactional(readOnly = false)
    @jakarta.transaction.Transactional
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

    // 포인트 선물하기 하단 리스트 조회
    @Override
    public GiftListOut getPointGiftList(GiftSearchConditionDto giftSearchConditionDto) {

        QGift gift = QGift.gift;

        // 적립 총 금액 구하기
        Long addTotalPoint = query
                .select(gift.point.longValue().sum())
                .from(gift)
                .where(gift.giftRecipientId.eq(giftSearchConditionDto.getUserId()))
                .fetchOne();

        // 사용 총 금액 구하기
        Long usedTotalPoint = query
                .select(gift.point.longValue().sum())
                .from(gift)
                .where(gift.giftSenderId.eq(giftSearchConditionDto.getUserId()))
                .fetchOne();

        // 선물내역 리스트 구하기
        BooleanBuilder builder = new BooleanBuilder();

        if(giftSearchConditionDto.getGiftGb().equals("all")) {
            builder.and(gift.giftRecipientId.eq(giftSearchConditionDto.getUserId())
                    .or(gift.giftSenderId.eq(giftSearchConditionDto.getUserId())));
        } else if(giftSearchConditionDto.getGiftGb().equals("send")) {
            builder.and(gift.giftSenderId.eq(giftSearchConditionDto.getUserId()));
        } else if(giftSearchConditionDto.getGiftGb().equals("received")) {
            // 내가 받는 사람인 경우에는 대기중인 선물 내역은 리스트에 표시하지 않음
            builder.and(gift.giftRecipientId.eq(giftSearchConditionDto.getUserId()));
        }

        List<GiftDetailDto> giftList = query
                .select(Projections.constructor(GiftDetailDto.class,
                        gift.id,
                        gift.point,
                        gift.giftType,
                        gift.giftMessage,
                        gift.giftSenderId,
                        gift.senderPointId,
                        gift.giftRecipientId,
                        gift.resultPointId,
                        gift.updatedDate
                        ))
                .from(gift)
                .where(builder)
                .orderBy(gift.updatedDate.desc())
                .offset(giftSearchConditionDto.getOffset())
                .limit(giftSearchConditionDto.getSize())
                .fetch();

        List<GiftDetailListOut> giftDetailListOut = new ArrayList<>();

        // TODO 포인트 내역리스트 가져오기에서 보여줘야할 선물 정보랑 비슷하니 공통메서드를 만들 수 있으면 만들 것

        if(!giftList.isEmpty()) {
            for(GiftDetailDto giftOne : giftList) {

                // 내가 받는 사람이고 수락 대기중인 선물인 경의 리스트 내역에 보여주지 않음
                if(giftOne.getGiftRecipientId().equals(giftSearchConditionDto.getUserId())
                        && giftOne.getGiftType().equals(GiftType.WAIT)) {
                    continue;
                }

                // 선물 상대 유저 정보 가져오기
                Long otherId = null;

                // 내가 보낸 사람인 경우
                if(giftOne.getGiftSenderId().equals(giftSearchConditionDto.getUserId()))
                    otherId = giftOne.getGiftRecipientId();
                else{
                    // 내가 받는 사람인 경우
                    otherId = giftOne.getGiftSenderId();
                }
                User user = userRepository.findById(otherId).orElseThrow(() -> new NoSuchElementException("User not found"));

                GiftDetailListOut giftDetail = GiftDetailListOut.builder()
                        .point(giftOne.getPoint())
                        .pointId(giftOne.getGiftSenderId().equals(
                                giftSearchConditionDto.getUserId()) ?
                                giftOne.getSenderPointId() : giftOne.getResultPointId())
                        .updatedDate(giftOne.getUpdatedDate())
                        .giftType(giftOne.getGiftType().getCode())
                        .messageOnOff(giftOne.getGiftMessage() == null ? false : true)
                        .giftId(giftOne.getGiftId())
                        .otherName(user.getName())
                        .otherId(user.getLoginId())
                        .build();

                giftDetailListOut.add(giftDetail);
            }
        }

        // 해당 유저의 선물 데이터 총 갯수 구하기
        Long totalRows = query
                .select(gift.count())
                .from(gift)
                .where(gift.giftRecipientId.eq(giftSearchConditionDto.getUserId())
                        .or(gift.giftSenderId.eq(giftSearchConditionDto.getUserId())))
                .fetchOne();

        return GiftListOut.builder()
                .aTotalPoint(addTotalPoint == null ? 0 : addTotalPoint.intValue())
                .uTotalPoint(usedTotalPoint == null ? 0 : usedTotalPoint.intValue())
                .giftDetailListOut(giftDetailListOut)
                .page(giftSearchConditionDto.getPage())
                .size(giftSearchConditionDto.getSize())
                .totalRows(totalRows == null ? 0 : totalRows)
                .build();
    }

    // 포인트 선물하기 하단 리스트 상세조회
    @Override
    public GiftPointDetailOut getGiftPointDetail(Long pointId) {

        Point point = pointRepository.findById(pointId).orElseThrow(() -> new NoSuchElementException("Point not found"));

        return GiftPointDetailOut.builder()
                .pointType(point.getPointType().getCode())
                .used(point.getUsed())
                .build();
    }

    // 선물 메세지 조회
    @Override
    public GiftMessageOut getGiftMessage(Long giftId) {

        Gift gift = giftRepository.findById(giftId).orElseThrow(() -> new NoSuchElementException("Gift not found"));

        return GiftMessageOut.builder()
                .giftMessage(gift.getGiftMessage())
                .giftImage(gift.getGiftImage())
                .build();
    }

}
