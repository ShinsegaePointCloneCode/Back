package com.example.smilekarina.coupon.application;

import com.example.smilekarina.coupon.dto.CouponDto;
import com.example.smilekarina.coupon.dto.CouponPartnerDto;
import com.example.smilekarina.coupon.vo.CouponAllSearchOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CouponService {
    void createPartner(CouponPartnerDto map);
    void createCoupon(CouponDto map);
    Page<CouponAllSearchOut> getAllCouponWithUser(Integer orderType, String token, Pageable pageable);
}
