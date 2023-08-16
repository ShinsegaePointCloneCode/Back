package com.example.smilekarina.club.infrastructure;

import com.example.smilekarina.club.domain.ClubCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubCarRepository extends JpaRepository<ClubCar, Long> {
}
