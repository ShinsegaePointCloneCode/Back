package com.example.smilekarina.notice.infrastructure;


import com.example.smilekarina.notice.domain.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQRepository extends JpaRepository<FAQ,Long> {

}
