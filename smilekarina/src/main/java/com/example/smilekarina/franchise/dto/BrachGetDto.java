package com.example.smilekarina.franchise.dto;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrachGetDto {
    private String branchAddress;
    private String branchName;
    /*
    private Float branchLatitude;   // 위도
    private Float branchLontitude;  // 경도
     */
}
