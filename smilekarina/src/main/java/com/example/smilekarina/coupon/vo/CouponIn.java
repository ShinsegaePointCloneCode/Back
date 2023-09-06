package com.example.smilekarina.coupon.vo;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@ToString
public class CouponIn {
    private String couponName;  //쿠폰명
    private LocalDateTime couponStart;    //쿠폰유효시작일
    private LocalDateTime couponEnd;  //쿠폰유효만료일
//    private Integer couponNum;  //쿠폰 수량
    private Integer couponType; //쿠폰 유형 ex)  True-퍼센트, False-금액
    private Double couponDiscount; //쿠폰 할인액 ex) 퍼센트일경우 :20 / 금액일 경우 : 2000
    private String couponPrecaution;    //유의사항
    private String couponImg;
    private Long couponPartnerId;
}
