package com.example.smilekarina.coupon.dto;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponPartnerDto {
    private String partnerName;   //쿠폰사용처 이름 ex)스피드메이트 전국지점
    private String imageUrl;  //쿠폰 사용처 이미지 -> 쿠폰 이미지 바로 아래에 있는 이미지
    private String imageUrlCircle;
    private String couponMerchandise;   //쿠폰 적용 가맹점
}
