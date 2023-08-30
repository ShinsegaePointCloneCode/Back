package com.example.smilekarina.agree.infrastructure;

import com.example.smilekarina.agree.domain.AgreeAdvertise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgreeAdvertiseRepository extends JpaRepository<AgreeAdvertise, Long> {

    Optional<AgreeAdvertise> findByUserId(Long userId);
}
