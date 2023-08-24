package com.example.smilekarina.event.infrastructure;

import com.example.smilekarina.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
     List<Event> findAll();
}
