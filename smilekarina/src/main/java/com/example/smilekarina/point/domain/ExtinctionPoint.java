package com.example.smilekarina.point.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtinctionPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "this_extiction_point")
    private Long thisExtinctionPoint;
    @Column(nullable = false, name = "next_extiction_point")
    private Long nextExtictionPoint;
    @Column(nullable = false, name = "user_id")
    private Long userId;
    @Column(nullable = false, name = "update_date")
    private LocalDate updateDate;
}
