package com.example.smilekarina.notice.infrastructure;

import com.example.smilekarina.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {

}
