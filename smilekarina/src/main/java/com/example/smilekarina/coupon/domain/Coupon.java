package com.example.smilekarina.coupon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//쿠폰
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //쿠폰 아이디
    @Column(nullable = false,length = 45, name="coupon_name")
    private String couponName;  //쿠폰명
    @Column(nullable = false, name="coupon_start")
    private LocalDateTime couponStart;    //쿠폰유효시작일
    @Column(nullable = false,name="coupon_end")
    private LocalDateTime couponEnd;  //쿠폰유효만료일
    @Column(nullable = false,name="coupon_num")
    private Integer couponNum;  //쿠폰 수량
    @Column(nullable = false, name="coupon_type")
    private Boolean couponType; //쿠폰 유형 ex)  True-퍼센트, False-금액
    @Column(nullable = false,name="coupon_discount")
    private Float couponDiscount; //쿠폰 할인액 ex) 퍼센트일경우 :20 / 금액일 경우 : 2000
    @Column(nullable = false,name = "coupon_precaution",columnDefinition = "TEXT")
    private String couponPrecaution;    //유의사항
    @Column(nullable = false, name="coupon_img", columnDefinition = "TEXT")
    private String couponImg; // todo: 쿠폰 이미지 -> 이미지 URL을 그대로 저장하고 있을꺼라서

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private CouponPartner couponPartner;  //coupon_parter_id
}
