package com.example.smilekarina.check.application;

import com.example.smilekarina.check.domain.CheckPoint;
import com.example.smilekarina.check.infrastructure.CheckRepository;
import com.example.smilekarina.global.exception.ErrorStateCode;
import com.example.smilekarina.global.exception.SameDayCheckException;
import com.example.smilekarina.point.application.PointService;
import com.example.smilekarina.point.domain.PointType;
import com.example.smilekarina.point.domain.PointTypeConverter;
import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
//@Transactional(readOnly = true)
public class CheckServiceImpl implements CheckService {
    private final UserService userService;
    private final CheckRepository checkRepository;
    private final PointService pointService;

    @Override
    public List<Integer> getCheck(String token) {
        Long userId = userService.getUserIdFromToken(token);
        LocalDate now = LocalDate.now();
        LocalDate firstDayOfMonth = now.withDayOfMonth(1);
        return checkRepository.findByUserIdAndCheckDateBetween(userId, firstDayOfMonth, now)
                .stream()
                .map(checkPoint -> checkPoint.getCheckDate().getDayOfMonth())
                .collect(Collectors.toList());
    }
    @Override
    @jakarta.transaction.Transactional
//    @Transactional(readOnly = false)
    public void createCheck(String token, LocalDate time) {
        Long userId = userService.getUserIdFromToken(token);
        Optional<CheckPoint> checkPoint = checkRepository.findFirstByUserIdOrderByCntDate(userId);
        if (checkPoint.isPresent()) {
            update(userId, checkPoint.get(), time);
        } else {
            create(userId, time);
        }
    }

    private void update(Long userId, CheckPoint lastPoint, LocalDate time) {
        LocalDate now = LocalDate.now();
        // 차이를 계산
        int dayDifference = time.getDayOfMonth() - lastPoint.getCheckDate().getDayOfMonth();
        // 계산 로직
        int newCntDate;
        // 하루 차이가 난 경우
        log.info(String.valueOf(dayDifference));
        if (dayDifference == 1) {
            newCntDate = lastPoint.getCntDate() + 1;
            pushPoint(userId, 1);
            if (newCntDate > 9) {
                newCntDate = 0; // 9를 넘어가면 0으로 설정
                pushPoint(userId, 10);
            }
            CheckPoint checkPoint = CheckPoint.builder()
                    .userId(userId)
                    .cntDate(newCntDate)
                    .checkDate(time)
                    .build();
            checkRepository.save(checkPoint);
        } else if(dayDifference < 1) {
            throw new SameDayCheckException(ErrorStateCode.SAMEDAYCHECK);
        } else {
            newCntDate = 1; // 차이가 1보다 크면 1로 설정
            CheckPoint checkPoint = CheckPoint.builder()
                    .userId(userId)
                    .cntDate(newCntDate)
                    .checkDate(time)
                    .build();
            checkRepository.save(checkPoint);
        }
    }

    private void create(Long userId, LocalDate time) {
        pushPoint(userId, 1);
        CheckPoint checkPoint = CheckPoint.builder()
                .cntDate(1)
                .userId(userId)
                .checkDate(time)
                .build();
        checkRepository.save(checkPoint);
    }
    private void pushPoint(Long userId, int point) {
        PointAddDto pointAddDto = PointAddDto.builder()
                .point(point)
                .pointType(PointType.CHECK.getCode())
                .used(false)
                .userId(userId)
                .build();
        pointService.registerPoint(pointAddDto);
    }
}
