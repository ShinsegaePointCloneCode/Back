package com.example.smilekarina.card.infrastructure;

import com.example.smilekarina.card.domain.MileageCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MileageCardRepository extends JpaRepository<MileageCard, Long> {

    List<MileageCard> findByUserId(Long userId);

}
