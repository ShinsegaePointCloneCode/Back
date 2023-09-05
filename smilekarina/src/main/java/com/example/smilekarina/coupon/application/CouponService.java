package com.example.smilekarina.coupon.application;

import com.example.smilekarina.coupon.dto.CouponPartnerDto;
import com.example.smilekarina.coupon.vo.CouponAllSearchOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CouponService {
    void createPartner(CouponPartnerDto map);
    Page<CouponAllSearchOut> getAllCoupon(String orderType, String token, Pageable pageable);
}
