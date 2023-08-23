package com.example.smilekarina.receipt.infrastructure;

import com.example.smilekarina.receipt.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

}
