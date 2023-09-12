package com.example.smilekarina.receipt.infrastructure;

import com.example.smilekarina.receipt.domain.SmartReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmartReceiptRepository extends JpaRepository<SmartReceipt, Long> {

    SmartReceipt findByPointId(Long pointId);

}
