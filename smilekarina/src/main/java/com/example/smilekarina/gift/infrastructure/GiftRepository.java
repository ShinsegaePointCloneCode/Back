package com.example.smilekarina.gift.infrastructure;

import com.example.smilekarina.gift.domain.Gift;
import com.example.smilekarina.gift.domain.GiftType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GiftRepository extends JpaRepository<Gift, Long> {

    Optional<Gift> findFirstByGiftRecipientIdAndGiftTypeOrderByIdDesc(Long gift_recipientId, GiftType giftType);

    Gift findByResultPointId(Long pointId);

    Gift findBySenderPointId(Long pointId);

}
