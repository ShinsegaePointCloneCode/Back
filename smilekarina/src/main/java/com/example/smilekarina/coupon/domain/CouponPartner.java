package com.example.smilekarina.coupon.domain;

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
//쿠폰 제휴사 ex) 메가박스, 스피드메이트
//따로 만든 이유: 이벤트 주체와 쿠폰주체는 달라서
//그렇다고 가맹점 리스트들과 연결시키기엔 가맹점 리스트는 포인트 사용가능처의 의미가 더 크기에 새로 만듦
public class CouponPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //쿠폰제휴사 id
    @Column(nullable = false,length = 75,name = "coupon_used_name")
    private String CouponUsedName;   //쿠폰사용처 이름 ex)스피드메이트 전국지점
    @Column(nullable = false, name="down_image_url")
    private Long downImageUrl;  //쿠폰 사용처 이미지 -> 쿠폰 이미지 바로 아래에 있는 이미지
    @Column(nullable = false,name="circl_image_url")    //쿠폰 사용처 이미지 -> 쿠폰 원형속 이미지
    private Long circleImageUrl;
}
