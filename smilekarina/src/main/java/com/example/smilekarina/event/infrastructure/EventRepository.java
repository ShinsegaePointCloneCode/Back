package com.example.smilekarina.event.infrastructure;

import com.example.smilekarina.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
     //querydsl
     //모두가져오기
     //최신순
     //임박순
     List<Event> findAll();

    List<Event> findByEventStartAfterAndEventEndBeforeOrderByRegDateDesc(LocalDateTime eventStart, LocalDateTime eventEnd);
    List<Event> findByEventStartAfterAndEventEndBeforeOrderByEventEndAsc(LocalDateTime eventStart, LocalDateTime eventEnd);
    List<Event> findByEventEndAfter(LocalDateTime endDateTime);
}
