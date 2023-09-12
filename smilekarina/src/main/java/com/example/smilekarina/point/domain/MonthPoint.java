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
public class MonthPoint {
    // 한달동안 포인트를 쌓은 유저의 포인트를 결산하는 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "user_id")
    private Long userId;
    @Column(nullable = false, name = "year_month_date")
    private LocalDate yearMonthDate;
    @Column(nullable = false, name = "month_point")
    private Long monthPoint;
    public void setMonthPoint(Long monthPoint) {
        this.monthPoint = monthPoint;
    }
    public void setYearMonthDate(LocalDate yearMonthDate) {
        this.yearMonthDate = yearMonthDate;
    }
}
