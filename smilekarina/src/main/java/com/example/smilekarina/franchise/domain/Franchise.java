package com.example.smilekarina.franchise.domain;

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
//가맹점 리스트
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;     //가맹점 아이디
    @Column(nullable = false,name="franchise_name",length = 255)
    private String franchiseName;   // 가맹점 이름
    @Column(nullable = false, name="image_id")
    private Long imageId;   // 이미지id
    @Column(nullable = false, name="b_combine",columnDefinition = "boolean default false")
    private Boolean bCombine;   // 통합로그인 제공유무 : 유: true, 무 : false /default 값 : 무
    @Column(nullable = false, name="b_getted_point", columnDefinition = "boolean defaul false")
    private Boolean bGettedPoint;   //결제적립제공유무: 유: true 무 : false /default 값 =무
    @Column(nullable = false, name="b_smart_receipt",columnDefinition = "boolean defaul false")
    private Boolean bSmartReceipt;  //스마트영수증제공유무: 유: true 무 : false /default 값 =무
}
