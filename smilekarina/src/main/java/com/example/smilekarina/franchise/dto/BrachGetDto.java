package com.example.smilekarina.franchise.dto;

import com.example.smilekarina.franchise.domain.Franchise;
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
    private String sidoName;
    private String gugunName;
    private Double branchLatitude;   // 위도
    private Double branchLontitude;  // 경도
    private Franchise franchise;
}
