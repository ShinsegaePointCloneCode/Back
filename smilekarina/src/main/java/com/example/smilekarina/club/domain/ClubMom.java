package com.example.smilekarina.club.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClubMom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "sex_first")
    private Boolean sexFirst; // todo: enum으로 바꿀까? 토론
    @Column(nullable = false, name = "birth_first")
    private LocalDate birthFirst;
    @Column(nullable = false, name = "sex_second")
    private Boolean sexSecond;
    @Column(nullable = false, name = "birth_second")
    private LocalDate birthSecond;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ClubList clubList;
}
