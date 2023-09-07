package com.example.smilekarina.event.infrastructure;

import com.example.smilekarina.event.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
     //querydsl
     //모두가져오기
     //최신순
     //임박순
    List<Event> findByEventEndAfter(LocalDateTime endDateTime);
}
