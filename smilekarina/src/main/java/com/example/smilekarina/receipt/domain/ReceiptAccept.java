package com.example.smilekarina.receipt.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 스마트 영수증 조회가 불가능한 곳에서 적립한 포인트
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptAccept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "point_id")
    private Long pointId;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "franchise_id")
    private Integer franchiseId;

    @Column(nullable = false, length = 45, name = "branch_name")
    private String branchName;

}
