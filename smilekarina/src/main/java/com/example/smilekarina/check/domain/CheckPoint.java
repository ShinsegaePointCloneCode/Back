package com.example.smilekarina.check.domain;

import com.example.smilekarina.global.domain.BaseEntity;
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
public class CheckPoint {
    // 출석체크
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "user_id")
    private Long userId;
    @Column(nullable = false, name= "cnt_date", columnDefinition = "int default 0")
    private Integer cntDate;
    @Column(nullable = false, name= "check_date")
    private LocalDate checkDate;
}
