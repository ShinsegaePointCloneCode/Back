package com.example.smilekarina.coupon.dto;
import lombok.*;
import java.time.LocalDateTime;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponUseGetDto {
    private String couponName;  //쿠폰명
    private LocalDateTime couponStart;    //쿠폰유효시작일
    private LocalDateTime couponEnd;  //쿠폰유효만료일
    private Boolean couponType; //쿠폰 유형 ex)  True-퍼센트, False-금액
    private Integer couponDiscount; //쿠폰 할인액 ex) 퍼센트일경우 :20 / 금액일 경우 : 2000
    private String couponPrecaution;
    private String couponImg;
    private String couponNumber;
    private String couponUsedName;
    private String couponMerchandise;
}
