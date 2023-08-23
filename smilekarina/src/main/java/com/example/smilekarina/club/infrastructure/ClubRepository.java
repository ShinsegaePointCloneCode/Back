package com.example.smilekarina.club.infrastructure;

import com.example.smilekarina.club.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {

}
