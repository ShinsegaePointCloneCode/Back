package com.example.smilekarina.club.infrastructure;

import com.example.smilekarina.club.domain.ClubList;
import com.example.smilekarina.club.domain.ClubType;
import com.example.smilekarina.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClubListRepository extends JpaRepository<ClubList, Long> {
    List<ClubList> findByUserAndClub_ClubType(User user, ClubType clubType);
    Optional<ClubList> findFirstByUserAndClub_ClubType(User user, ClubType clubType);
    List<ClubList> findByUser(User user);
}
