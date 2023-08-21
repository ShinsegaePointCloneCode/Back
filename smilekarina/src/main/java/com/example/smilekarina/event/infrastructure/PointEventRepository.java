package com.example.smilekarina.event.infrastructure;

import com.example.smilekarina.event.domain.PointEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointEventRepository extends JpaRepository<PointEvent,Long>{


}
