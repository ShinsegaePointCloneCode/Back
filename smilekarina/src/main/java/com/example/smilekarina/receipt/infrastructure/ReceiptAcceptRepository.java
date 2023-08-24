package com.example.smilekarina.receipt.infrastructure;

import com.example.smilekarina.receipt.domain.ReceiptAccept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptAcceptRepository extends JpaRepository<ReceiptAccept, Long> {

}
