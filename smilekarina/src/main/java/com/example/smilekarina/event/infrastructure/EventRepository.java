package com.example.smilekarina.event.infrastructure;

import com.example.smilekarina.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {

}
