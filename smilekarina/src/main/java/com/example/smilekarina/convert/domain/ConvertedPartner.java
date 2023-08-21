package com.example.smilekarina.convert.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 전환 제휴사
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConvertedPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "converted_partner_name")
    private String convertedPartnerName;

}
