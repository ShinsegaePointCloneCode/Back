package com.example.smilekarina.coupon.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
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
    public CouponAllSearchOut(
            String partnerName,
            String imageUrl,
            String imageUrlCircle,
            String couponMerchandise,
            Long couponId,
            String couponName,
            LocalDateTime couponStart,
            LocalDateTime couponEnd,
            String couponImg,
            String couponPrecaution,
            Boolean useStatus,
            String couponNumber
    ) {

        this.partnerName = partnerName;
        this.imageUrl = imageUrl;
        this.imageUrlCircle = imageUrlCircle;
        this.couponMerchandise = couponMerchandise;
        this.couponId = couponId;
        this.couponName = couponName;
        this.couponStart = couponStart;
        this.couponEnd = couponEnd;
        this.couponImg = couponImg;
        this.couponPrecaution = couponPrecaution;
        this.useStatus = useStatus;
        this.couponNumber = couponNumber;
    }
}
