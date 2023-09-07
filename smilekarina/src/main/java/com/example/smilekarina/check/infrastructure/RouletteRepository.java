package com.example.smilekarina.check.infrastructure;

import com.example.smilekarina.check.domain.Roulette;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface RouletteRepository extends JpaRepository<Roulette, Long> {
    Optional<Roulette> findByRouletteDateAndUserId(LocalDate now, Long userId);
}
