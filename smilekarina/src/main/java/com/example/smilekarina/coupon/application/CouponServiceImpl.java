package com.example.smilekarina.coupon.application;

import com.example.smilekarina.coupon.domain.*;
import com.example.smilekarina.coupon.dto.CouponPartnerDto;
import com.example.smilekarina.coupon.infrastructure.CouponPartnerRepository;
import com.example.smilekarina.coupon.infrastructure.CouponRepository;
import com.example.smilekarina.coupon.infrastructure.MyCouponListRepository;
import com.example.smilekarina.coupon.vo.CouponAllSearchOut;
import com.example.smilekarina.user.application.UserService;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CouponServiceImpl implements CouponService{
    private final CouponRepository couponRepository;
    private final CouponPartnerRepository couponPartnerRepository;
    private final MyCouponListRepository myCouponListRepository;
    private final UserService userService;
    private final JPAQueryFactory query;

//    @Override
//    public List<?> checkIngCoupon(Integer pageNo, Integer size, String orderType) {
//        LocalDateTime currentTime = LocalDateTime.now();
//        List<Coupon> ingCoupon;
//        if(orderType.equals("DESC")){
//            //마감임박
//            ingCoupon=couponRepository.findByCouponStartAfterAndCouponEndOrderByCouponEndDesc(currentTime,currentTime);
//        } else if(orderType.equals("ASC")){
//            //최신순
//            ingCoupon=couponRepository.findByCouponStartAfterAndCouponEndOrderByCouponEndAsc(currentTime,currentTime);
//        }
//        return null;
//    }
//    @Override
//    public List<?> endCoupon(String searchType, Integer pageNo, Integer size) {
//        LocalDateTime now = LocalDateTime.now();
//        List<Coupon> endCoupon;
//        if(searchType.equals("EXPIRED")){
//            endCoupon=couponRepository.findByCouponEndAfter(now);
//        //} else if (searchType.equals("USED")) {
////            endCoupon=couponRepository.
//        }
//        return null;
//    }
    @Override
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
    public Page<CouponAllSearchOut> getAllCoupon(String orderType, String token, Pageable pageable) {
        QCoupon coupon = QCoupon.coupon;
        QCouponPartner couponPartner = QCouponPartner.couponPartner;
        QMyCouponList myCouponList = QMyCouponList.myCouponList;
        LocalDateTime now = LocalDateTime.now();
        Long userId = userService.getUserIdFromToken(token);
        // 쿠폰 기간에서 order Type마다 다르게 배열 한다.
        // useStatus는 userId에 해당하는 userStatus를 사용한다.
        List<CouponAllSearchOut> couponAllSearchOut = query
                .select(Projections.constructor(CouponAllSearchOut.class,
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
                .where(
                        coupon.couponStart.loe(now),
                        coupon.couponEnd.goe(now),
                        myCouponList.userId.eq(userId)
                        )
                .orderBy(coupon.couponStart.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query
                .select(coupon.count())
                .from(coupon)
                .leftJoin(myCouponList).on(myCouponList.coupon.id.eq(coupon.id))
                .where(
                        coupon.couponStart.loe(now),
                        coupon.couponEnd.goe(now),
                        myCouponList.userId.eq(userId)
                )
                .fetchOne();
        if (count == null) count = 0L;
        return new PageImpl<>(couponAllSearchOut,pageable,count);
    }
}
