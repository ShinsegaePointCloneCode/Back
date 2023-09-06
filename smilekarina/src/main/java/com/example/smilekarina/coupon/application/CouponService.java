package com.example.smilekarina.coupon.application;

import com.example.smilekarina.coupon.dto.CouponDto;
import com.example.smilekarina.coupon.dto.CouponPartnerDto;
import com.example.smilekarina.coupon.vo.CouponAllSearchOut;
import com.example.smilekarina.coupon.vo.CouponGetIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CouponService {
    void createPartner(CouponPartnerDto map);
    void createCoupon(CouponDto map);
    Page<CouponAllSearchOut> getAllCouponWithUser(Integer orderType, String token, Pageable pageable);
    void createMyCoupon(String token, Long couponId);
    Page<CouponAllSearchOut> getAllMyCoupon(Integer orderType, String token, Pageable pageable);
    void createAllMyCoupon(String token, List<Long> couponList);
    Page<CouponAllSearchOut> getAllCoupon(Integer orderType, Pageable pageable);
}
