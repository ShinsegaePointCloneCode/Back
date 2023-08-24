package com.example.smilekarina.coupon.dto;

import jakarta.persistence.Column;

public class CouponPartnerGetDto {
    private String CouponUsedName;   //쿠폰사용처 이름 ex)스피드메이트 전국지점
    private Long downImageUrl;  //쿠폰 사용처 이미지 -> 쿠폰 이미지 바로 아래에 있는 이미지
    private Long circleImageUrl;
}
