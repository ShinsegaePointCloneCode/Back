package com.example.smilekarina.franchise.vo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseOut {
    private String franchiseName;
    private String franchiseImage;
    private String branchName;
    private String branchAddress;
    private Double branchLatitude;
    private Double branchLontitude;
}
