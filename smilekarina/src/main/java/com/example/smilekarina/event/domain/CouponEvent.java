package com.example.smilekarina.event.domain;

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
//쿠폰제공 이벤트
public class CouponEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //쿠폰제공이벤트 id ->coupon_offer_id
    @Column(nullable = false,name = "coupon_id")
    private Long couponId; //쿠폰 id
    @Column(nullable = false, name = "event_id")
    private Long eventId;   //이벤트 id
}
