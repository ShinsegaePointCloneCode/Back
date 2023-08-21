package com.example.smilekarina.event.dto;

import jakarta.persistence.Column;
import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponEventGetDto {
    private Long couponId; //쿠폰 id
    private Long eventId;   //이벤트 id
}
