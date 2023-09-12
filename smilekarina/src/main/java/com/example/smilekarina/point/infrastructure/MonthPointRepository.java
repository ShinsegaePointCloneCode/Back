package com.example.smilekarina.point.infrastructure;

import com.example.smilekarina.point.domain.MonthPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MonthPointRepository extends JpaRepository<MonthPoint, Long> {
    Optional<MonthPoint> findByYearMonthDateAndUserId(LocalDate startDate, Long userId);
}
