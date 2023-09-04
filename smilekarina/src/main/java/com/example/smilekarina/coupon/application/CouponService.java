package com.example.smilekarina.coupon.application;

import com.example.smilekarina.coupon.vo.CouponPageListOut;

import java.util.List;

public interface CouponService {
    List<CouponPageListOut> checkIngCoupon(Integer pageNo,Integer size, String orderType);

    List<CouponPageListOut> endCoupon(String searchType, Integer pageNo, Integer size);
}
