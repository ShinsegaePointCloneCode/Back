package com.example.smilekarina.club.domain;

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
public class ClubMom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sex_first")
    private Boolean sexFirst; // todo: enum으로 바꿀까? 토론
    @Column(name = "birth_first")
    private LocalDate birthFirst;
    @Column(name = "sex_second")
    private Boolean sexSecond;
    @Column(name = "birth_second")
    private LocalDate birthSecond;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ClubList clubList;
}
