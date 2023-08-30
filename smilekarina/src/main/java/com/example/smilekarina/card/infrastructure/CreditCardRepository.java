package com.example.smilekarina.card.infrastructure;

import com.example.smilekarina.card.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
