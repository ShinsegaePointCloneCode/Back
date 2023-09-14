package com.example.smilekarina.point.infrastructure;

import com.example.smilekarina.point.domain.Point;
import com.example.smilekarina.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface PointRepository extends JpaRepository<Point, Long> {
    Point findFirstByUserOrderByIdDesc(User user);

    Point findFirstByUserIdAndCreatedDateBeforeOrderByCreatedDateDesc(Long userId, LocalDateTime endOfYesterday);
}
