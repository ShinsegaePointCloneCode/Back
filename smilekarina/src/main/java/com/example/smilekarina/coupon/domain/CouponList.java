package com.example.smilekarina.coupon.domain;

import com.example.smilekarina.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//쿠폰 리스트
public class CouponList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //쿠폰리스트 id
    @Column(nullable = false, name="use_status",columnDefinition = "boolean default false")
    //사용했으면 true, 사용하지 않았으면 false
    private Boolean useStatus;
    @Column(nullable = false, length = 30, name = "coupon_number")
    private String couponNumber;    //쿠폰 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;  //user id
    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;  //coupon id
}
