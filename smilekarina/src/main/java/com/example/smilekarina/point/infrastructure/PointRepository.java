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
//    List<Point> findByUserId(Long userId);

    // 사용 가능 포인트 찾기 - 적립예정인 포인트 제외, id 순으로 내림차순 정렬 limit 1
//    Optional<Point> findFirstByUserAndCreatedDateBeforeOrderByIdDesc(User user, LocalDateTime date);

    // 해당 유저의 가장 최신 포인트 정보 가져 오기
    Point findFirstByUserOrderByIdDesc(User user);

    // 위에거 안되면 이걸로 실행 예정
//    Integer getUsablePoint(User user, LocalDateTime date);

    // 오늘 적립 포인트 찾기 - 포인트유형이 스마트영수증적립, 적립인 데이터의 합을 추출

//    @Query(value = "SELECT SUM(point) FROM point "
//            + "WHERE pointType in ('SM','AC') AND "
//            + "createdDate BETWEEN :startDate AND :endDate ",
//            nativeQuery = false)
//    Integer getTodayAcceptPoint(@Param("userId") Long userId,
//                                @Param("startDate")LocalDateTime startDate,
//                                @Param("endDate")LocalDateTime endDate);

}
