package com.example.smilekarina.coupon.vo;

import lombok.Setter;

import java.time.LocalDateTime;
@Setter
public class CouponAllSearchOut {
    //coupon partner
    private String partnerName;   //쿠폰사용처 이름 ex)스피드메이트 전국지점
    private String imageUrl;
    private String imageUrlCircle;
    private String couponMerchandise;
    // coupon
    private Long couponId;
    private String couponName;
    private LocalDateTime couponStart;
    private LocalDateTime couponEnd;
    private String couponImg;
    private String couponPrecaution;
    // my coupon list
    private Boolean useStatus;
    private String couponNumber;

}
