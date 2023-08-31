package com.example.smilekarina.check.infrastructure;

import com.example.smilekarina.check.domain.CheckPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CheckRepository extends JpaRepository<CheckPoint, Long> {
    List<CheckPoint> findByUserIdAndCheckDateBetween(Long userId, LocalDate createdDate, LocalDate createdDate2);
    Optional<CheckPoint> findFirstByUserIdOrderByCntDate(Long userId);
}
