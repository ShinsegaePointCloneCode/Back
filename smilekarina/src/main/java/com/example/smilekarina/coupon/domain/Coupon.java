package com.example.smilekarina.coupon.domain;

import com.example.smilekarina.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.EnumMap;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(nullable = false,name="coupon_img")
    private Integer couponNum;  //쿠폰 수량
    @Column(nullable = false, name="coupon_type")
    private Boolean couponType; //쿠폰 유형 ex)  True-퍼센트, False-금액
    @Column(nullable = false,name="coupon_discount")
    private Integer couponDiscount; //쿠폰 할인액 ex)
    /*
    @Column(nullable = false, name="coupon_img")
    private Long couponImg; // todo: 쿠폰 이미지 -> 이미지 id 받아서 사용 image id는 BIGINT이기에 Long
     */

}
