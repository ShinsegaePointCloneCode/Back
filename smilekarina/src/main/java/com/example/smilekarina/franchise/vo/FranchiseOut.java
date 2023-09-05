package com.example.smilekarina.franchise.vo;

import com.example.smilekarina.franchise.domain.Branch;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseOut {
    private String franchiseName;
    private String franchiseImage;
    private String sidoName;
    private String gugunName;
    private String branchName;
    private String branchAddress;
    private Double branchLatitude;
    private Double branchLontitude;
    public FranchiseOut(Branch branch, String franchiseImage, String franchiseName) {
        this.sidoName = branch.getSidoName();
        this.gugunName = branch.getGugunName();
        this.branchName = branch.getBranchName();
        this.branchAddress = branch.getBranchAddress();
        this.branchLatitude = branch.getBranchLatitude();
        this.branchLontitude = branch.getBranchLontitude();
        this.franchiseImage = franchiseImage;
        this.franchiseName = franchiseName;
    }
}
