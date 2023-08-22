package com.example.smilekarina.club.domain;

import com.example.smilekarina.point.domain.PointType;
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
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "club_type")
    @Convert(converter = ClubTypeConverter.class)
    private ClubType clubType;
    @Column(name = "club_content",columnDefinition = "TEXT")
    private String clubContent;
}
