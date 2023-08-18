package com.example.smilekarina.card.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 카드제휴사
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45, name = "card_partner_name")
    private String cardPartnerName;

}
