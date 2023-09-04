package com.example.smilekarina.coupon.application;

import com.example.smilekarina.coupon.domain.Coupon;
import com.example.smilekarina.coupon.infrastructure.CouponRepository;
import com.example.smilekarina.coupon.vo.CouponPageListOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponServiceImpl implements CouponService{
    private final CouponRepository couponRepository;

    @Override
    public List<CouponPageListOut> checkIngCoupon(Integer pageNo, Integer size, String orderType) {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Coupon> ingCoupon;
        if(orderType.equals("DESC")){
            //마감임박
            ingCoupon=couponRepository.findByCouponStartAfterAndCouponEndOrderByCouponEndDesc(currentTime,currentTime);
        } else if(orderType.equals("ASC")){
            //최신순
            ingCoupon=couponRepository.findByCouponStartAfterAndCouponEndOrderByCouponEndAsc(currentTime,currentTime);
        }
        return null;
    }

    @Override
    public List<CouponPageListOut> endCoupon(String searchType, Integer pageNo, Integer size) {
        LocalDateTime now = LocalDateTime.now();
        List<Coupon> endCoupon;
        if(searchType.equals("EXPIRED")){
            endCoupon=couponRepository.findByCouponEndAfter(now);
        //} else if (searchType.equals("USED")) {
//            endCoupon=couponRepository.
        }
        return null;
    }

}
