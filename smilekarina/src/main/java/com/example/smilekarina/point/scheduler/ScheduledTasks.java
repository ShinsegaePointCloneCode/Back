package com.example.smilekarina.point.scheduler;
//
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import com.example.smilekarina.point.application.PointService;
import com.example.smilekarina.point.domain.QPoint;
import com.example.smilekarina.user.application.UserService;
import com.example.smilekarina.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Component
@RequiredArgsConstructor
//@EnableBatchProcessing
public class ScheduledTasks {
    private final UserService userService;
    private final PointService pointService;
    private final JPAQueryFactory query;
    @Scheduled(cron = "0 0 2 * * ?")  // 초 분 시 일 월 요일(일 1, 월 2--)
    public void executeAllUser() {
        List<User> users = userService.getAllUsers();
        LocalDate targetDate = LocalDate.now().minusDays(1);
        for (User user : users) {
            pointService.getExtinctionPoints(user.getId(),targetDate);
        }
        System.out.println("Executed task at " + LocalDateTime.now());
    }
    // 매달 결산한 유저의 한달간 모은 데이터를 만들어주는 스케줄러
    @Scheduled(cron = "0 0 1 * * ?")
    public void collectMonthPay() {
        // 대상 달은 이전 달이므로 minusDays(1)을 사용
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime targetDate = now.minusDays(1);
        LocalDateTime startDate = targetDate.withDayOfMonth(1);
        LocalDateTime endDate = now;
        QPoint point = QPoint.point1;
        // 한 달 간 포인트를 쌓은 유저의 ID 리스트를 가져오는 쿼리
        List<Long> userIds = query
                .select(point.user.id)
                .from(point)
                .where(point.createdDate.between(startDate, endDate))
                .fetch();

        // 각 유저의 ID에 대해 amountMonthPoint 서비스 메소드 호출
        for (Long userId : userIds) {
            pointService.amountMonthPoint(userId, targetDate.toLocalDate());
            log.info(String.valueOf(userId));

        }
    }
}
