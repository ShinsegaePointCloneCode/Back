package com.example.smilekarina.coupon.application;

import com.example.smilekarina.coupon.domain.*;
import com.example.smilekarina.coupon.dto.CouponDto;
import com.example.smilekarina.coupon.dto.CouponPartnerDto;
import com.example.smilekarina.coupon.infrastructure.CouponPartnerRepository;
import com.example.smilekarina.coupon.infrastructure.CouponRepository;
import com.example.smilekarina.coupon.infrastructure.MyCouponListRepository;
import com.example.smilekarina.coupon.vo.CouponAllSearchOut;
import com.example.smilekarina.coupon.vo.CouponGetIn;
import com.example.smilekarina.user.application.UserService;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@Transactional(readOnly = true)
public class CouponServiceImpl implements CouponService{
    private final CouponRepository couponRepository;
    private final CouponPartnerRepository couponPartnerRepository;
    private final MyCouponListRepository myCouponListRepository;
    private final UserService userService;
    private final JPAQueryFactory query;

    @Override
//    @Transactional(readOnly = false)
    public void createPartner(CouponPartnerDto dto) {
        CouponPartner couponPartner = CouponPartner.builder()
                .partnerName(dto.getPartnerName())
                .imageUrl(dto.getImageUrl())
                .imageUrlCircle(dto.getImageUrlCircle())
                .couponMerchandise(dto.getCouponMerchandise())
                .build();
        couponPartnerRepository.save(couponPartner);
    }
    @Override
//    @Transactional(readOnly = false)
    public void createCoupon(CouponDto dto) {
//        log.info(String.valueOf(dto.getCouponPartnerId()));
        CouponPartner couponPartner = couponPartnerRepository.findById(dto.getCouponPartnerId()).orElse(null);
//        log.info(String.valueOf(couponPartner.getId()));
        Coupon coupon = Coupon.builder()
                .couponName(dto.getCouponName())
                .couponStart(dto.getCouponStart())
                .couponEnd(dto.getCouponEnd())
                .couponImg(dto.getCouponImg())
                .couponType(dto.getCouponType())
                .couponDiscount(dto.getCouponDiscount())
                .couponPrecaution(dto.getCouponPrecaution())
                .couponPartner(couponPartner)
                .build();
        couponRepository.save(coupon);
    }

    @Override
    public Page<CouponAllSearchOut> getAllCouponWithUser(Integer orderType, String token, Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = userService.getUserIdFromToken(token);
        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(QCoupon.coupon, orderType);
//        log.info("쿼리 시작==>" + now);
        List<CouponAllSearchOut> couponAllSearchOut = fetchCoupons(
                QCoupon.coupon, QCouponPartner.couponPartner, QMyCouponList.myCouponList,
                now, userId, true, orderSpecifier, pageable);
        Long count = getCount(QCoupon.coupon, now);
        return new PageImpl<>(couponAllSearchOut, pageable, count);
    }
    @Override
    public Page<CouponAllSearchOut> getAllCoupon(Integer orderType, Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(QCoupon.coupon, orderType);
        List<CouponAllSearchOut> couponAllSearchOut = fetchNoUserCoupons(
                QCoupon.coupon, QCouponPartner.couponPartner, QMyCouponList.myCouponList,
                now, true, orderSpecifier, pageable);
        Long count = getCount(QCoupon.coupon, now);
        return new PageImpl<>(couponAllSearchOut, pageable, count);
    }

    @Override
    public Page<CouponAllSearchOut> getAllMyCoupon(Integer orderType, String token, Pageable pageable) {
        // 현재 시간
        LocalDateTime now = LocalDateTime.now();
        Long userId = userService.getUserIdFromToken(token);
        // 정렬 타입 정의
        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(QCoupon.coupon, orderType);
        List<CouponAllSearchOut> couponAllSearchOut = fetchCoupons(
                QCoupon.coupon, QCouponPartner.couponPartner, QMyCouponList.myCouponList,
                now, userId, false, orderSpecifier, pageable);
        Long count = getCount(QCoupon.coupon, now);
        return new PageImpl<>(couponAllSearchOut, pageable, count);
    }

    @Override
//    @Transactional(readOnly = false)
    public void createMyCoupon(String token, Long couponId) {
        Long userId = userService.getUserIdFromToken(token);
        Coupon coupon = couponRepository.findById(couponId).orElse(null);
        // todo : 유효성 검사
        generateCoupon(coupon,userId);

    }

    @Override
//    @Transactional(readOnly = false)
    public void createAllMyCoupon(String token, List<Long> couponList) {
        Long userId = userService.getUserIdFromToken(token);
        // 최대 20개 이하
        // todo : 유효성 검사
        couponList.stream()
                .map(couponRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(coupon -> generateCoupon(coupon, userId));
    }



    // 타입에 따른 정렬 기준
    private OrderSpecifier<?> getOrderSpecifier(QCoupon coupon, Integer orderType) {
        return switch (orderType) {
            case 30 -> coupon.id.asc();
            case 40 -> coupon.couponEnd.desc();
            default -> coupon.couponStart.asc();  // 기본 정렬
        };
    }

    private List<CouponAllSearchOut> fetchCoupons(
            QCoupon coupon, QCouponPartner couponPartner, QMyCouponList myCouponList,
            LocalDateTime now, Long userId, Boolean includeAll, OrderSpecifier<?> orderSpecifier, Pageable pageable) {
        BooleanExpression baseCondition = coupon.couponStart.loe(now).and(coupon.couponEnd.goe(now));
        BooleanExpression userCondition = includeAll ? null : myCouponList.userId.eq(userId);

        return query.select(Projections.constructor(CouponAllSearchOut.class,
                        couponPartner.partnerName,
                        couponPartner.imageUrl,
                        couponPartner.imageUrlCircle,
                        couponPartner.couponMerchandise,
                        coupon.id,
                        coupon.couponName,
                        coupon.couponStart,
                        coupon.couponEnd,
                        coupon.couponImg,
                        coupon.couponPrecaution,
                        myCouponList.useStatus,
                        myCouponList.couponNumber
                ))
                .from(coupon)
                .leftJoin(coupon.couponPartner, couponPartner)
                .leftJoin(myCouponList).on(myCouponList.coupon.id.eq(coupon.id), myCouponList.userId.eq(userId))
                .where(baseCondition)
                .where(userCondition)
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
    private List<CouponAllSearchOut> fetchNoUserCoupons(
            QCoupon coupon, QCouponPartner couponPartner, QMyCouponList myCouponList,
            LocalDateTime now,  Boolean includeAll, OrderSpecifier<?> orderSpecifier, Pageable pageable) {
        BooleanExpression baseCondition = coupon.couponStart.loe(now).and(coupon.couponEnd.goe(now));


        return query.select(Projections.constructor(CouponAllSearchOut.class,
                        couponPartner.partnerName,
                        couponPartner.imageUrl,
                        couponPartner.imageUrlCircle,
                        couponPartner.couponMerchandise,
                        coupon.id,
                        coupon.couponName,
                        coupon.couponStart,
                        coupon.couponEnd,
                        coupon.couponImg,
                        coupon.couponPrecaution,
                        myCouponList.useStatus,
                        myCouponList.couponNumber
                ))
                .from(coupon)
                .leftJoin(coupon.couponPartner, couponPartner)
                .leftJoin(myCouponList).on(myCouponList.coupon.id.eq(coupon.id))
                .where(baseCondition)
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
    // 페이지 네이션 카운트(쿠폰)
    private Long getCount(QCoupon coupon, LocalDateTime now) {
        Long count = query.select(coupon.count())
                .from(coupon)
                .leftJoin(QMyCouponList.myCouponList).on(QMyCouponList.myCouponList.coupon.id.eq(coupon.id))
                .where(coupon.couponStart.loe(now), coupon.couponEnd.goe(now))
                .fetchOne();
        return count == null ? 0L : count;
    }
    // 쿠폰 생성
    private void generateCoupon(Coupon coupon, Long userId) {
        String couponNumber = generateRandomNumber(20);
        MyCouponList myCouponList = MyCouponList.builder()
                .useStatus(false)
                .coupon(coupon)
                .couponNumber(couponNumber)
                .userId(userId)
                .downloadDate(LocalDateTime.now())
                .build();
        myCouponListRepository.save(myCouponList);
    }
    // 바코드 번호 추출(외부 서비스)
    private String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // 0 ~ 9 사이의 숫자
        }
        return sb.toString();
    }
}
