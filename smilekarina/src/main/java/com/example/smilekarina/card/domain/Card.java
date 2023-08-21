package com.example.smilekarina.card.domain;

import com.example.smilekarina.global.domain.BaseEntity;
import com.example.smilekarina.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 제휴포인트카드
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45, name = "description")
    private String description; // TODO 종류도 enum으로 정리해야 할수도 있으니 추후 확인

    @Column(nullable = false, length = 45, name = "card_number")
    private String cardNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private CardPartner cardPartner;

}
