package com.example.smilekarina.point.infrastructure;

import com.example.smilekarina.point.domain.Point;
import com.example.smilekarina.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {
    Point findFirstByUserOrderByIdDesc(User user);

    Point findFirstByUserIdAndCreatedDateBeforeOrderByCreatedDateDesc(Long userId, LocalDateTime endOfYesterday);
}
