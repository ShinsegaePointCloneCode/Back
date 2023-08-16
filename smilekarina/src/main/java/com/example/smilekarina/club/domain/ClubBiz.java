package com.example.smilekarina.club.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClubBiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50, name = "biz_company")
    private String bizCompany;
    @Column(nullable = false, name = "biz_reg_number")
    private Integer bizRegNumber;
    @Column(nullable = false, length = 200, name = "biz_reg_presentation")
    private String bizRepresentation;
    @Column(nullable = false, length = 100, name = "biz_address")
    private String bizAddress;
    @Column(nullable = false, length = 100, name = "biz_email")
    private String bizEmail;
    @Column(nullable = false, name = "person_info")
    private Boolean personalInfo;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private ClubList clubList;
}
