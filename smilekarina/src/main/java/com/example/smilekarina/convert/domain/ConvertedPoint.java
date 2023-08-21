package com.example.smilekarina.convert.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 전환 포인트
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConvertedPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "point_id")
    private Long pointId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ConvertedPartner convertedPartner;

    @Column(nullable = false, name = "user_id")
    private Long userId;

}
