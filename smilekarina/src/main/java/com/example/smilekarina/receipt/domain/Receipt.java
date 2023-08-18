package com.example.smilekarina.receipt.domain;

import com.example.smilekarina.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 영수증
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Receipt extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,  length = 30 , name = "receipt_number")
    private String receiptNumber;

}
