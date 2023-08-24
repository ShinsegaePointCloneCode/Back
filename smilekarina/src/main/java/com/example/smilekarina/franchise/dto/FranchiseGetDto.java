package com.example.smilekarina.franchise.dto;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseGetDto {
    private String franchiseName;   // 가맹점 이름
    private Long imageId;   // 이미지id
    private Boolean bCombine;   // 통합로그인 제공유무 : 유: true, 무 : false /default 값 : 무
    private Boolean bGettedPoint;   //결제적립제공유무: 유: true 무 : false /default 값 =무
    private Boolean bSmartReceipt;  //스마트영수증제공유무: 유: true 무 : false /default 값 =무
}
