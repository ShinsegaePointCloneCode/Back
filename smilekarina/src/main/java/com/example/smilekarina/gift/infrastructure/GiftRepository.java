package com.example.smilekarina.gift.infrastructure;

import com.example.smilekarina.gift.domain.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftRepository extends JpaRepository<Gift, Long> {

}
