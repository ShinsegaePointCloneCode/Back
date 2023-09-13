package com.example.smilekarina.franchise.vo;

import com.example.smilekarina.franchise.domain.Branch;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseOut {
    private Integer branchId;
    private String franchiseName;
    private String franchiseImage;
    private String branchName;
    private String branchAddress;
    private Double branchLatitude;
    private Double branchLontitude;
    public FranchiseOut(Branch branch, String franchiseImage, String franchiseName) {
        this.branchName = branch.getBranchName();
        this.branchAddress = branch.getBranchAddress();
        this.branchLatitude = branch.getBranchLatitude();
        this.branchLontitude = branch.getBranchLontitude();
        this.franchiseImage = franchiseImage;
        this.franchiseName = franchiseName;
    }
}
