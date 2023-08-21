package com.example.smilekarina.club.infrastructure;

import com.example.smilekarina.club.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
