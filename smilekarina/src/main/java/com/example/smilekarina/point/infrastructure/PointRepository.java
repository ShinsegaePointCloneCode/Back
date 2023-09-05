package com.example.smilekarina.point.infrastructure;

import com.example.smilekarina.point.domain.Point;
import com.example.smilekarina.user.domain.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {

    // 해당 유저의 가장 최신 포인트 정보 가져 오기
    Point findFirstByUserOrderByIdDesc(User user);

    // 특정 달의 가장 마지막에 쌓인 포인트 정보 가져 오기
    Point findFirstByUserAndCreatedDateBeforeOrderByIdDesc(
            User user, LocalDateTime targetDate);



}
