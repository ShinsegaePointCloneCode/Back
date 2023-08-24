package com.example.smilekarina.franchise.domain;

import jakarta.persistence.*;

@Entity
//지점
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length=100, name="branch_address")
    private String branchAddress;
    @Column(nullable = false, length = 45, name="branch_name")
    private String branchName;

    @Column(nullable = false,name = "branch_latitude")
    private Float branchLatitude;   // 위도
    @Column(nullable = false,name = "branch_lontitude")
    private Float branchLontitude;  // 경도

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Franchise franchise;    //프랜차이즈 id
}
