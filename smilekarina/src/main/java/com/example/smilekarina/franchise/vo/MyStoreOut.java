package com.example.smilekarina.franchise.vo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyStoreOut {
    private Integer branchId;
    private String franchiseName;
    private String franchiseImage;
    private String branchName;
    private String branchAddress;
}
