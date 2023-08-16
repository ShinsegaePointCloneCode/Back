package com.example.smilekarina.coupon.domain;

import com.example.smilekarina.user.domain.User;
import jakarta.persistence.*;

@Entity
public class CouponList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name="use_status",columnDefinition = "boolean default false")
    //사용했으면 true, 사용하지 않았으면 false
    private Boolean useStatus;
    @Column(nullable = false, length = 30, name = "coupon_number")
    private String couponNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
