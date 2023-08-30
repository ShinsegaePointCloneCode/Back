package com.example.smilekarina.card.infrastructure;

import com.example.smilekarina.card.domain.PointCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointCardRepository  extends JpaRepository<PointCard, Long> {


}
