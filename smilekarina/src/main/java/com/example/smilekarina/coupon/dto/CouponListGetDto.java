package com.example.smilekarina.coupon.dto;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponListGetDto {
    private Boolean useStatus;
    private String couponNumber;    //쿠폰 번호
}
