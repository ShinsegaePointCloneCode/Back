package com.example.smilekarina.point.application;

import com.example.smilekarina.point.domain.*;
import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.point.dto.PointPasswordCheckDto;
import com.example.smilekarina.point.infrastructure.ExtinctionPointRepository;
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
            Integer extPoint = extinctionPoint.getThisExtinctionPoint();
            // 다다음달 소멸예정 포인트 조회
            Integer extNextPoint = extinctionPoint.getNextExtictionPoint();

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

    // 소멸예정포인트 조회
    private Integer getExtinctionPoints(User user, LocalDate todayDate) { // 계산해야할 현재 달 - minusMonth
        QPoint point = QPoint.point1;

        // For last_month:
        LocalDate lastMonthStartDate = todayDate.minusYears(2).withDayOfMonth(1);
        LocalDateTime lastMonthStartDateTime = lastMonthStartDate.atStartOfDay();

        LocalDate lastMonthEndDate = lastMonthStartDate.plusMonths(1);
        LocalDateTime lastMonthEndDateTime = lastMonthEndDate.atStartOfDay();

        Integer last_month = query
                .select(point.point.sum())
                .from(point)
                .where(point.user.eq(user)
                        .and(point.createdDate.between(lastMonthStartDateTime, lastMonthEndDateTime))
                        .and(point.used.eq(false)))
                .fetchOne();

        last_month = (last_month != null) ? last_month : 0;

        // For next_month:
        LocalDate nextMonthStartDate = lastMonthEndDate;
        LocalDateTime nextMonthStartDateTime = nextMonthStartDate.atStartOfDay();

        LocalDate nextMonthEndDate = nextMonthStartDate.plusMonths(1);
        LocalDateTime nextMonthEndDateTime = nextMonthEndDate.atStartOfDay();

        Integer next_month = query
                .select(point.point.sum())
                .from(point)
                .where(point.user.eq(user)
                        .and(point.createdDate.between(nextMonthStartDateTime, nextMonthEndDateTime))
                        .and(point.used.eq(false)))
                .fetchOne();

        next_month = (next_month != null) ? next_month : 0;
        // 매달 1일 0시에 소멸예정 포인트를 결산해 준다. 해당 달에 쌓은 포인트를 만든 테이블을 만들어 준다.

        // 2. 소멸예정 기준 달의 다음달부터 어제까지 사용한 포인트 값의 합 가져오기
        // 어제까지 쌓았던 포인트를 들고와서 오늘부터(?) 쓴것부터 2년전까지 리스트로 들고온다.

        // 들고온것을 시간 역순으로 뺀다. 해당 달이 2년전부터 2달 내이면 그것을 내보낸다.

        return null;
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
