package com.example.smilekarina.coupon.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyCouponListGetDto {
    private String couponName;
    private String couponUsedName;   //쿠폰사용처 이름 ex)스피드메이트 전국지점
    private String couponImg;
    private String downImageUrl;  //쿠폰 사용처 이미지 -> 쿠폰 이미지 바로 아래에 있는 이미지
    private String circleImageUrl;
    private LocalDateTime couponStart;
    private LocalDateTime couponEnd;
    private Boolean useStatus;
    private String couponNumber;    //쿠폰 번호
}
