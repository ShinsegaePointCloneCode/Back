package com.example.smilekarina.coupon.vo;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponPageListOut {
    private Long couponId;
    private LocalDateTime couponStart;
    private LocalDateTime couponEnd;
    private String couponImg;
    private String couponPrecaution;
}
