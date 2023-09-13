package com.example.smilekarina.point.infrastructure;

import com.example.smilekarina.point.domain.ExtinctionPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ExtinctionPointRepository  extends JpaRepository<ExtinctionPoint, Long> {
    Optional<ExtinctionPoint> findByUpdateDateAndUserId(LocalDate now, Long userId);
}
