package com.example.smilekarina.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AllCouponGetIn {
    private List<Long> couponList;
}
