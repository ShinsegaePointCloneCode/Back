package com.example.smilekarina.coupon.vo;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CouponPartnerIn {
    private String partnerName;   //쿠폰사용처 이름 ex)스피드메이트 전국지점
    private String imageUrl;  //쿠폰 사용처 이미지 -> 쿠폰 이미지 바로 아래에 있는 이미지
    private String imageUrlCircle;
    private String couponMerchandise;   //쿠폰 적용 가맹점
}
