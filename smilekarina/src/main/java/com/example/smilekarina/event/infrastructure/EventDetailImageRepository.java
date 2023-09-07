package com.example.smilekarina.event.infrastructure;

import com.example.smilekarina.event.domain.EventDetailImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventDetailImageRepository extends JpaRepository<EventDetailImage,Long> {
    List<EventDetailImage> findByEventId(Long eventId);

}
