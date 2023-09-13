package com.example.smilekarina.franchise.vo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegionOut {
    private Integer branchId;
    private String franchiseName;
    private String sidoName;
    private String gugunName;
    private String branchName;
    private String branchAddress;
}
