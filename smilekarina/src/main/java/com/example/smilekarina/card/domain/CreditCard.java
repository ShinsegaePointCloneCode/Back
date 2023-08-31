package com.example.smilekarina.card.domain;

import com.example.smilekarina.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 제휴신용카드
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45, name = "card_number")
    private String cardNumber;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, length = 45, name = "card_name")
    private String cardName;

    @Column(nullable = false, length = 45, name = "issue_place")
    private String issuePlace;
}
