package com.example.smilekarina.point.application;

import com.example.smilekarina.point.domain.*;
import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.point.dto.PointPasswordCheckDto;
import com.example.smilekarina.point.infrastructure.ExtinctionPointRepository;
import com.example.smilekarina.point.infrastructure.MonthPointRepository;
import com.example.smilekarina.point.infrastructure.PointRepository;
import com.example.smilekarina.point.vo.PointInfoOut;
import com.example.smilekarina.user.application.UserService;
import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.infrastructure.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Transactional(readOnly = true)
public class  PointServiceImpl implements PointService{

    private final UserService userService;
    private final PointRepository pointRepository;
    private final UserRepository userRepository;
    private final MonthPointRepository monthPointRepository;
    private final ExtinctionPointRepository extinctionPointRepository;
    private final JPAQueryFactory query;

    // 사용가능포인트 조회
    @Override
    public Integer getUsablePoint(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        return getUsablePoint(user);
    }

    // 포인트 내역 상단 조회
    @Override
    public PointInfoOut getPointInfo(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);

        // 사용가능 포인트 조회
        Integer usablPoint = getUsablePoint(user);
        // 적립예정 포인트 조회
        Integer addPoint = getAddExpectedPoint(user);
        // extictionPoint에서 받아온다. 혹시 null 이면 0을 반환한다.
        ExtinctionPoint extinctionPoint = extinctionPointRepository.findByUpdateDateAndUserId(LocalDate.now(),userId)
                .orElse(null);
        if (extinctionPoint == null) {
            return PointInfoOut.builder()
                    .totalPoint(usablPoint)
                    .addPoint(addPoint)
                    .extPoint(0)
                    .extNextPoint(0)
                    .build();
        } else {
            // 다음달 소멸예정 포인트 조회
            Integer extPoint = Math.toIntExact(extinctionPoint.getThisExtinctionPoint());
            // 다다음달 소멸예정 포인트 조회
            Integer extNextPoint = Math.toIntExact(extinctionPoint.getNextExtictionPoint());

            return PointInfoOut.builder()
                    .totalPoint(usablPoint)
                    .addPoint(addPoint)
                    .extPoint(extPoint)
                    .extNextPoint(extNextPoint)
                    .build();
        }
    }


    // 포인트 생성
    @Override
    @Transactional(readOnly = false)
    public Long registerPoint(PointAddDto pointAddDto) {

        User user = userRepository.findById(pointAddDto.getUserId()).orElseThrow(IllegalArgumentException::new);

        // TotalPoint 계산을 위해 가장 최신 포인트 정보 가져 오기
        Point lastPoint = pointRepository.findFirstByUserOrderByIdDesc(user);

        Integer totalPoint = 0;
        if(lastPoint != null) {
            if(pointAddDto.getUsed()) {
                totalPoint = lastPoint.getTotalPoint() - pointAddDto.getPoint();
            } else {
                totalPoint = lastPoint.getTotalPoint() + pointAddDto.getPoint();
            }
        } else {
            totalPoint = pointAddDto.getPoint();
        }

        // 포인트 타입 설정
        PointType pointType = new PointTypeConverter().convertToEntityAttribute(pointAddDto.getPointType());

        Point resultPoint = pointRepository.save(Point.builder()
                .point(pointAddDto.getPoint())
                .totalPoint(totalPoint)
                .pointType(pointType)
                .user(user)
                .used(pointAddDto.getUsed())
                .build());

        return resultPoint.getId();
    }

    // 포인트 비밀번호 일치 확인
    @Override
    public Boolean checkPointPassword(PointPasswordCheckDto pointPasswordCheckDto) {

        User user = userRepository.findById(pointPasswordCheckDto.getUserId()).orElseThrow(IllegalArgumentException::new);

        // 포인트 비밀번호가 없거나 일치하지 않은 경우
        if(user.getPointPassword() == null || !user.getPointPassword().equals(pointPasswordCheckDto.getPointPassword())) {
          return false;
        }

        return true;
    }

    // 포인트 비밀번호 수정
    @Override
    @Transactional(readOnly = false)
    public void modifyPointPassword(String token, String pointPassword) {

        // 토큰 정보에서 userId 값 가져 오기
        Long userId = userService.getUserIdFromToken(token);

        // 수정대상 유저정보 가져오기
        Optional<User> user = userRepository.findById(userId);

        // User 객체가 존재할 경우만 내부 로직 실행
        user.ifPresent(modifiedUser -> {
            modifiedUser.setPointPassword(pointPassword);
        });
    }

    // 사용가능포인트 조회
    private Integer getUsablePoint(User user) {

        // 1. 해당 유저의 제일 마지막에 등록된 포인트 데이터의 전체포인트값 가져오기
        Point lastPoint = pointRepository.findFirstByUserOrderByIdDesc(user);

        // 아직포인트가 한번도 등록된 적이 없다면 0을 return
        if(lastPoint == null) {
            return 0;
        }

        Integer lastTotalPoint = lastPoint.getTotalPoint();

        // 2. 오늘 기준으로 [스마트영수증], [일반] 으로 적립된 데이터 합계 가져오기
        Integer addExpectedPoint = getAddExpectedPoint(user);

        // 1.의 값 - 2.값을 계산해서 return
        return lastTotalPoint - addExpectedPoint;
    }


    // 적립예정포인트 조회
    private Integer getAddExpectedPoint(User user) {

        QPoint point = QPoint.point1;

        PointType general = new PointTypeConverter().convertToEntityAttribute(PointType.GENERAL.getCode());
        PointType smartReceipt = new PointTypeConverter().convertToEntityAttribute(PointType.SMARTRECEIPT.getCode());

        LocalDateTime today = createStartLocalDateTime(LocalDate.now());

        // QueryDSL에서 sum은 Long타입으로 반환한다.
        Long addExpectedPoint = query
                .select(point.point.longValue().sum())
                .from(point)
                .where(point.user.eq(user))
                .where(point.pointType.eq(general).or(point.pointType.eq(smartReceipt)))
                .where(point.used.eq(false))
                .where(point.createdDate.after(today))
                .fetchOne();

        if(addExpectedPoint == null) {
            return 0;
        }

        return addExpectedPoint.intValue();
    }

    // 한달간 쌓은 포인트 결산 메소드
    @Override
    @Transactional(readOnly = false)
    public void amountMonthPoint(Long userId, LocalDate targetDate) {
        QPoint point = QPoint.point1;

        // 해당 달의 처음과 끝을 기준으로 설정
        LocalDate startDate = targetDate.withDayOfMonth(1);
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDate endDate = startDate.plusMonths(1);
        LocalDateTime endDateTime = endDate.atStartOfDay();

        // 해당 유저의 월 포인트 가져오기
        Optional<MonthPoint> monthPointOpt = monthPointRepository.findByYearMonthAndUserId(startDate, userId);

        // 해당 유저에 맞는 id를 비교하여 포인트 합계 계산
        Integer sum = query
                .select(point.point.sum())
                .from(point)
                .where(point.user.id.eq(userId)
                        .and(point.createdDate.between(startDateTime, endDateTime)))
                .fetchOne();

        sum = (sum != null) ? sum : 0;  // If the sum is null, set it to 0

        if (monthPointOpt.isPresent()) {
            // 데이터가 있으면 해당 날짜로 날짜를 바꿔주고, 합계도 바꿔준다.
            MonthPoint existingMonthPoint = monthPointOpt.get();
            existingMonthPoint.setYearMonth(startDate);
            existingMonthPoint.setMonthPoint(Long.valueOf(sum));
            monthPointRepository.save(existingMonthPoint);
        } else {
            // 데이터가 없으면 새로운 데이터를 만들어 준다.
            MonthPoint monthPoint = MonthPoint.builder()
                    .yearMonth(startDate)
                    .monthPoint(Long.valueOf(sum))
                    .userId(userId)
                    .build();
        }
    }



    // 소멸예정포인트 결산
    @Transactional(readOnly = false)
    public void getExtinctionPoints(Long userId, LocalDate todayDate) { // 계산해야할 현재 달
        // 어제까지 pointRepo에 마지막에 저장된 내역을 들고와서 계산할 포인트를 정한다.
        LocalDateTime endOfYesterday = todayDate.atStartOfDay();
        Point lastPointBeforeToday = pointRepository.findFirstByUserIdAndCreatedDateBeforeOrderByCreatedDateDesc(userId, endOfYesterday);

        if (lastPointBeforeToday == null) {
            // 해당 User에 대한 오늘 이전의 포인트가 없는 경우
            ExtinctionPoint.builder()
                    .userId(userId)
                    .thisExtinctionPoint(0L)
                    .nextExtictionPoint(0L)
                    .build();
            return;
        }
        // 계산할 포인트 설정
        int extraPoint = lastPointBeforeToday.getTotalPoint();

        LocalDate endPeriod = todayDate.minusYears(2).plusMonths(2).with(TemporalAdjusters.lastDayOfMonth());
        LocalDate currentMonthLastDay = todayDate.with(TemporalAdjusters.lastDayOfMonth());
        // 2달전에 계산된 모든 포인트를 현재포인트로 뺀다. 이를 통해 소멸 예정 포인트를 구한다.
        while (!currentMonthLastDay.isBefore(endPeriod)) {
            MonthPoint currentMonthPoint = monthPointRepository.findByYearMonthAndUserId(currentMonthLastDay, userId).orElse(null);

            if (currentMonthPoint != null) {
                extraPoint -= currentMonthPoint.getMonthPoint().intValue();
            }
            currentMonthLastDay = currentMonthLastDay.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

            // 만약 extraPoint가 0 이하가 되면 lastMonth와 nextMonth를 0으로 설정하고, while문을 벗어남
            if (extraPoint <= 0) {
                break;
            }
        }

        long lastMonth = 0L;
        long nextMonth = 0L;

        if (extraPoint < 0) {
            // 만약 소멸될 포인트가 없는 경우
            ExtinctionPoint.builder()
                    .userId(userId)
                    .thisExtinctionPoint(0L)
                    .nextExtictionPoint(0L)
                    .build();
            return;
        }

        // 소멸 예정 포인트 구하기
        LocalDate twoYearsAgoThisMonthLastDay = todayDate.minusYears(2).with(TemporalAdjusters.lastDayOfMonth());
        LocalDate twoYearsAgoNextMonthLastDay = twoYearsAgoThisMonthLastDay.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        MonthPoint lastMonthPoint = monthPointRepository.findByYearMonthAndUserId(twoYearsAgoThisMonthLastDay, userId).orElse(null);
        MonthPoint nextMonthPoint = monthPointRepository.findByYearMonthAndUserId(twoYearsAgoNextMonthLastDay, userId).orElse(null);

        if (nextMonthPoint != null) {
            extraPoint -= nextMonthPoint.getMonthPoint().intValue();
            if (extraPoint < 0) {
                // 만약 1 - 10 을 했다면 nextMonth에 1포인트를 준다.
                nextMonth = nextMonthPoint.getMonthPoint().intValue() + extraPoint;
                ExtinctionPoint.builder()
                        .userId(userId)
                        .thisExtinctionPoint(0L)
                        .nextExtictionPoint(nextMonth)
                        .build();
                return;
            }
        }

        if (lastMonthPoint != null) {
            extraPoint -= lastMonthPoint.getMonthPoint().intValue();
            if (extraPoint < 0) {
                lastMonth =  lastMonthPoint.getMonthPoint().intValue() + extraPoint;
                ExtinctionPoint.builder()
                        .userId(userId)
                        .thisExtinctionPoint(lastMonth)
                        .nextExtictionPoint(nextMonthPoint.getMonthPoint())
                        .build();
                return;
            }
        }


        ExtinctionPoint.builder()
                .userId(userId)
                .thisExtinctionPoint(lastMonthPoint.getMonthPoint())
                .nextExtictionPoint(nextMonthPoint.getMonthPoint())
                .build();
    }



    // 날짜(뒤에 시간은 00:00:00) 만들기
    private LocalDateTime createStartLocalDateTime(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.of(0,0,0));
    }

    // 날짜(뒤에 시간은 23:59:59) 만들기
//    private LocalDateTime createEndLocalDateTime(LocalDate date) {
//        return LocalDateTime.of(date, LocalTime.of(23,59,59));
//    }

    // 밑에는 강사님 코드 참고용 ************************************

//    @Override
//    @Convert(converter = PointTypeConverter.class)
//    public void createPoint(PointAddDto pointAddDto) {
//
//        User getUser = userRepository.findByLoginId(pointAddDto.getLoginId()).get();
//        log.info("user is : {}" , getUser);
//
//        PointType pointType = new PointTypeConverter().convertToEntityAttribute(pointAddDto.getPointType());
//
//        //todo TotalPoint 계산
//
//        pointRepository.save(Point.builder()
//                .point(pointAddDto.getPoint())
//                .totalPoint(pointAddDto.getPoint())
//                .pointType(pointType)
//                .user(getUser)
//                .used(pointAddDto.getUsed())
//                .build());
//    }
//
//    @Override
//    @Convert(converter = PointTypeConverter.class)
//    public List<PointGetDto> getPointByUser(Long userId) {
//        List<Point> pointList = pointRepository.findByUserId(userId);
//        List<PointGetDto> pointGetDtoList = pointList.stream().map(point -> {
//                    PointType pointType = new PointTypeConverter().convertToEntityAttribute(point.getPointType().getCode());
//                    return PointGetDto.builder()
//                            .pointType(pointType.getValue())
//                            .point(point.getPoint())
//                            .used(point.getUsed())
//                            .build();
//                }
//        ).toList();
//        log.info("pointList is : {}" , pointList);
//        return pointGetDtoList;
//    }
}
