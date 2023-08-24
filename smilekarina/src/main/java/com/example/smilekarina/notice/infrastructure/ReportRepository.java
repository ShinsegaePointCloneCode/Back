package com.example.smilekarina.notice.infrastructure;

import com.example.smilekarina.notice.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ReportRepository extends JpaRepository<Report,Long> {
    List<Report> findByUserId(Long userId);
}
