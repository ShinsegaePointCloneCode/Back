package com.example.smilekarina.point.application;

import com.example.smilekarina.gift.dto.GiftDetailDto;
import com.example.smilekarina.point.domain.*;
import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.point.dto.PointDetailDto;
import com.example.smilekarina.point.dto.PointPasswordCheckDto;
import com.example.smilekarina.point.dto.PointSearchConditionDto;
import com.example.smilekarina.point.infrastructure.ExtinctionPointRepository;
import com.example.smilekarina.point.infrastructure.MonthPointRepository;
import com.example.smilekarina.point.infrastructure.PointRepository;
import com.example.smilekarina.point.vo.PointContentOut;
import com.example.smilekarina.point.vo.PointDetailOut;
import com.example.smilekarina.point.vo.PointInfoOut;
import com.example.smilekarina.point.vo.PointListOut;
import com.example.smilekarina.user.application.UserService;
import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.exception.NoUserException;
import com.example.smilekarina.user.exception.UserErrorStateCode;
import com.example.smilekarina.user.infrastructure.UserRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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

        User user = userRepository.findById(userId).orElseThrow(()-> new NoUserException(UserErrorStateCode.NOUSER));
        return getUsablePoint(user);
    }

    // 포인트 내역 상단 조회
    @Override
    public PointInfoOut getPointInfo(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new NoUserException(UserErrorStateCode.NOUSER));

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

        User user = userRepository.findById(pointAddDto.getUserId())
                .orElseThrow(()-> new NoUserException(UserErrorStateCode.NOUSER));

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

        User user = userRepository.findById(pointPasswordCheckDto.getUserId())
                .orElseThrow(()-> new NoUserException(UserErrorStateCode.NOUSER));

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

    // 포인트 리스트 조회
    @Override
    public PointListOut getPointList(PointSearchConditionDto pointSearchConditionDto) {

        User user = userService.getUserFromToken(pointSearchConditionDto.getToken());

        QPoint point = QPoint.point1;

        // 고정 조건
        LocalDate startDate = LocalDate.parse(pointSearchConditionDto.getRangeStartDate(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(pointSearchConditionDto.getRangeEndDate(), DateTimeFormatter.ISO_DATE);
        BooleanExpression baseCondition = point.user.eq(user)
                .and(point.createdDate.between(createStartLocalDateTime(startDate),
                        createEndLocalDateTime(endDate)));

        // 그외 조건
        // 포인트 리스트 조건 설정 - pointType
        BooleanBuilder pointTypeBuilder = new BooleanBuilder();
        String pointType = pointSearchConditionDto.getPointType();
        if(pointType.equals("used")) {
            pointTypeBuilder.and(point.pointType.in(PointType.GIFT,PointType.CANCELGIFT,PointType.CONVERT).not());
        } else if(pointType.equals("gift")) {
            pointTypeBuilder.and(point.pointType.in(PointType.GIFT,PointType.CANCELGIFT));
        } else if(pointType.equals("convert")) {
            pointTypeBuilder.and(point.pointType.eq(PointType.CONVERT));
        }

        // 포인트 리스트 조건 설정 - usedType
        String usedType = pointSearchConditionDto.getUsedType();
        BooleanBuilder usedTypeBuilder = new BooleanBuilder();
        if(usedType.equals("false")) {
            usedTypeBuilder.and(point.used.eq(false));
        } else if(usedType.equals("true")) {
            usedTypeBuilder.and(point.used.eq(true));
        }

        // 포인트 리스트 조건 설정 - pointHistoryType
        String pointHistoryType = pointSearchConditionDto.getPointHistoryType();
        BooleanBuilder pointHistoryTypeBuilder = new BooleanBuilder();
        if(pointHistoryType.equals("event")) {
            pointHistoryTypeBuilder.and(point.pointType.in(PointType.CHECK,PointType.ROULETTE,PointType.EVENT));
        } else if(pointHistoryType.equals("general")) {
            pointHistoryTypeBuilder.and(point.pointType.in(PointType.CHECK,PointType.ROULETTE,PointType.EVENT)).not();
        }

        // 포인트 적립/사용구분(usedType)이 [전체]이거나 [적립]인 경우에는 적립 총 금액 구하기
        Long addTotalPoint = 0L;
        if(usedType.equals("all") || usedType.equals("false")) {
            addTotalPoint = query
                    .select(point.point.longValue().sum())
                    .from(point)
                    .where(baseCondition)
                    .where(pointTypeBuilder)
                    .where(point.used.eq(false))
                    .where(pointHistoryTypeBuilder)
                    .fetchOne();
        }

        // 포인트 적립/사용구분(usedType)이 [전체]이거나 [사용]인 경우에는 사용 총 금액 구하기
        Long usedTotalPoint = 0L;
        if(usedType.equals("all") || usedType.equals("true")) {
            usedTotalPoint = query
                    .select(point.point.longValue().sum())
                    .from(point)
                    .where(baseCondition)
                    .where(pointTypeBuilder)
                    .where(point.used.eq(true))
                    .where(pointHistoryTypeBuilder)
                    .fetchOne();
        }

        String tmp = PointType.SMARTRECEIPT.getValue();

        // 포인트 리스트 구하기
        List<PointDetailDto> pointDetailDtoList = query
                .select(Projections.constructor(PointDetailDto.class,
                        point.id,
                        point.point,
                        point.used,
                        point.pointType,
                        point.createdDate
                ))
                .from(point)
                .where(baseCondition)
                .where(pointTypeBuilder)
                .where(usedTypeBuilder)
                .where(pointHistoryTypeBuilder)
                .orderBy(point.createdDate.desc())
                .orderBy(point.used.desc())
                .offset(pointSearchConditionDto.getOffset())
                .limit(pointSearchConditionDto.getSize())
                .fetch();

        List<PointDetailOut> pointDetailOutList = new ArrayList<>();
        if(pointDetailDtoList != null) {
            pointDetailOutList = pointDetailDtoList.stream().map(pointDetail -> {
                return PointDetailOut.builder()
                        .pointId(pointDetail.getPointId())
                        .point(pointDetail.getPoint())
                        .used(pointDetail.getUsed())
                        .pointType(pointDetail.getPointType().getValue())
                        .showDate(pointDetail.getCreatedDate())
                        .build();
            }).toList();
        }

        // 해당 유저의 포인트 데이터 총 갯수 구하기
        Long totalRows = query
                .select(point.count())
                .from(point)
                .where(baseCondition)
                .where(pointTypeBuilder)
                .where(usedTypeBuilder)
                .where(pointHistoryTypeBuilder)
                .fetchOne();

        return PointListOut.builder()
                .aTotalPoint(addTotalPoint == null ? 0 : addTotalPoint)
                .uTotalPoint(usedTotalPoint == null ? 0 : usedTotalPoint)
                .pointDetailOutList(pointDetailOutList)
                .page(pointSearchConditionDto.getPage())
                .size(pointSearchConditionDto.getSize())
                .totalRows(totalRows == null ? 0 : totalRows)
                .build();
    }

    // 포인트 리스트 상세 내역 조회 - 출석, 룰렛
    @Override
    public PointContentOut getEventPoint(String pointType) {
        return PointContentOut.builder()
                .franchiseName("신세계포인트닷컴")
                .addDetailDesc(PointType.CHECK.getValue().equals(pointType) ?
                        "[APP] 출석체크 포인트 지급" : "[APP] 럭키룰렛 포인트 지급")
                .build();
    }

    // 포인트 리스트 상세 내역 조회 - 소멸
    @Override
    public PointContentOut getExtinction() {
        return PointContentOut.builder()
                .franchiseName("신세계") // 신세계 소멸 밖에 본 적이 없어서 일단 고정
                .addDetailDesc("포인트 유효기간 경과 소멸")
                .build();
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
        Optional<MonthPoint> monthPointOpt = monthPointRepository.findByYearMonthDateAndUserId(startDate, userId);

        // 해당 유저에 맞는 id를 비교하여 포인트 합계 계산
        Integer sum = query
                .select(point.point.sum())
                .from(point)
                .where(point.user.id.eq(userId)
                        .and(point.createdDate.between(startDateTime, endDateTime)))
                .fetchOne();
        log.info(String.valueOf(sum));
        sum = (sum != null) ? sum : 0;  // If the sum is null, set it to 0
        if (monthPointOpt.isPresent()) {
            // 데이터가 있으면 해당 날짜로 날짜를 바꿔주고, 합계도 바꿔준다.
            MonthPoint existingMonthPoint = monthPointOpt.get();
            existingMonthPoint.setYearMonthDate(startDate);
            existingMonthPoint.setMonthPoint(Long.valueOf(sum));
            monthPointRepository.save(existingMonthPoint);
        } else {
            // 데이터가 없으면 새로운 데이터를 만들어 준다.
            MonthPoint monthPoint = MonthPoint.builder()
                    .yearMonthDate(startDate)
                    .monthPoint(Long.valueOf(sum))
                    .userId(userId)
                    .build();
            monthPointRepository.save(monthPoint);
        }
    }

    // 소멸예정포인트 결산
    @Override
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
                    .updateDate(todayDate)
                    .build();
            return;
        }
        // 계산할 포인트 설정
        int extraPoint = lastPointBeforeToday.getTotalPoint();

        LocalDate endPeriod = todayDate.minusYears(2)
                .plusMonths(2).with(TemporalAdjusters.lastDayOfMonth());
        LocalDate currentMonthLastDay = todayDate.with(TemporalAdjusters.lastDayOfMonth());
        // 2달전에 계산된 모든 포인트를 현재포인트로 뺀다. 이를 통해 소멸 예정 포인트를 구한다.
        int count = 0;
        while (!currentMonthLastDay.isBefore(endPeriod)) {
            count += 1;
            MonthPoint currentMonthPoint = monthPointRepository.findByYearMonthDateAndUserId(currentMonthLastDay, userId)
                    .orElse(null);
            if (count > 22) break;
            else if (currentMonthPoint == null) continue;
            extraPoint -= currentMonthPoint.getMonthPoint().intValue();
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
                    .updateDate(todayDate)
                    .build();
            return;
        }

        // 소멸 예정 포인트 구하기
        LocalDate twoYearsAgoThisMonthLastDay = todayDate.minusYears(2).with(TemporalAdjusters.lastDayOfMonth());
        LocalDate twoYearsAgoNextMonthLastDay = twoYearsAgoThisMonthLastDay.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        MonthPoint lastMonthPoint = monthPointRepository.findByYearMonthDateAndUserId(twoYearsAgoThisMonthLastDay, userId).orElse(null);
        MonthPoint nextMonthPoint = monthPointRepository.findByYearMonthDateAndUserId(twoYearsAgoNextMonthLastDay, userId).orElse(null);

        if (nextMonthPoint != null) {
            extraPoint -= nextMonthPoint.getMonthPoint().intValue();
            if (extraPoint < 0) {
                // 만약 1 - 10 을 했다면 nextMonth에 1포인트를 준다.
                nextMonth = nextMonthPoint.getMonthPoint().intValue() + extraPoint;
                ExtinctionPoint extinctionPoint = ExtinctionPoint.builder()
                        .userId(userId)
                        .thisExtinctionPoint(0L)
                        .nextExtictionPoint(nextMonth)
                        .updateDate(todayDate)
                        .build();
                extinctionPointRepository.save(extinctionPoint);
                return;
            }
        }

        if (lastMonthPoint != null) {
            extraPoint -= lastMonthPoint.getMonthPoint().intValue();
            if (extraPoint < 0) {
                lastMonth =  lastMonthPoint.getMonthPoint().intValue() + extraPoint;
                ExtinctionPoint extinctionPoint = ExtinctionPoint.builder()
                        .userId(userId)
                        .thisExtinctionPoint(lastMonth)
                        .nextExtictionPoint(nextMonthPoint.getMonthPoint())
                        .updateDate(todayDate)
                        .build();
                extinctionPointRepository.save(extinctionPoint);
                return;
            }
        }


        ExtinctionPoint extinctionPoint = ExtinctionPoint.builder()
                .userId(userId)
                .thisExtinctionPoint(0L)
                .nextExtictionPoint(0L)
                .updateDate(todayDate)
                .build();
        extinctionPointRepository.save(extinctionPoint);
    }

    // 날짜(뒤에 시간은 00:00:00) 만들기
    private LocalDateTime createStartLocalDateTime(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.of(0,0,0));
    }

    // 날짜(뒤에 시간은 23:59:59) 만들기
    private LocalDateTime createEndLocalDateTime(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.of(23,59,59));
    }

}
