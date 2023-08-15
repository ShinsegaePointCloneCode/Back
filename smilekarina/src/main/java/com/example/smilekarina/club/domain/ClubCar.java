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
public class ClubCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30, name = "region_number")
    private String regionNumber;
    @Column(nullable = false, name = "car_number")
    private Integer carNumber;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ClubList clubList;
}
