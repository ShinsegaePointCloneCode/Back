package com.example.smilekarina.point.application;

import com.example.smilekarina.point.domain.Point;
import com.example.smilekarina.point.domain.PointType;
import com.example.smilekarina.point.domain.PointTypeConverter;
import com.example.smilekarina.point.domain.QPoint;
import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.point.dto.PointPasswordCheckDto;
import com.example.smilekarina.point.infrastructure.PointRepository;
import com.example.smilekarina.point.vo.PointInfoOut;
import com.example.smilekarina.user.application.UserService;
import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.infrastructure.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
//@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class  PointServiceImpl implements PointService{

    private final UserService userService;
    private final PointRepository pointRepository;
    private final UserRepository userRepository;
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
        // 다음달 소멸예정 포인트 조회
        Integer extPoint = getExtinction(user,24L);
        // 다다음달 소멸예정 포인트 조회
        Integer extNextPoint = getExtinction(user,23L);

        return PointInfoOut.builder()
                .totalPoint(usablPoint)
                .addPoint(addPoint)
                .extPoint(extPoint)
                .extNextPoint(extNextPoint)
                .build();
    }

    // 포인트 생성
    @Override
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
    @Transactional
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
    private Integer getExtinction(User user, Long minusMonth) {

        // 1. 해당 달의 마지막에 등록된 포인트 데이터의 전체포인트값 가져오기

        // 1-1) 소멸예정달의 마지막에 등록된 포인트 데이터의 전체포인트값 가져오기
        LocalDate targetMonthDate = LocalDate.now().minusMonths(minusMonth-1);
        LocalDate targetMonthFirstDate = targetMonthDate.withDayOfMonth(1);
        LocalDateTime targetMonthDateTime = createStartLocalDateTime(targetMonthFirstDate);

        Point lastPoint = pointRepository.findFirstByUserAndCreatedDateBeforeOrderByIdDesc(
                user, targetMonthDateTime);

        // 해당 달에 쌓인 포인트가 없다면 0을 반환
        if(lastPoint == null) {
            return 0;
        }

        Integer extPoint = lastPoint.getTotalPoint();

        // 1-2) 소멸예정 달 직전 달의 마지막에 등록된 포인트 데이터의 전체포인값 가져오기
        LocalDate targetLastMonthDate = LocalDate.now().minusMonths(minusMonth);
        LocalDate targetLastMonthStartDate = targetLastMonthDate.withDayOfMonth(1);
        LocalDateTime targetLastMonthDateTime = createStartLocalDateTime(targetLastMonthStartDate);

        Point lastMonthLastPoint = pointRepository.findFirstByUserAndCreatedDateBeforeOrderByIdDesc(
                user, targetLastMonthDateTime);

        // 직전 달의 전체포인트값은 제외
        if(lastMonthLastPoint != null) {
            extPoint = extPoint - lastMonthLastPoint.getTotalPoint();
        }

        // 2. 소멸예정 기준 달의 다음달부터 어제까지 사용한 포인트 값의 합 가져오기
        QPoint point = QPoint.point1;

        LocalDateTime today = createStartLocalDateTime(LocalDate.now());

        // QueryDSL에서 sum은 Long타입으로 반환한다.
        Long usedPoint = query
                .select(point.point.longValue().sum())
                .from(point)
                .where(point.user.eq(user))
                .where(point.used.eq(true))
                .where(point.createdDate.goe(targetMonthDateTime))
                .where(point.createdDate.lt(today))
                .fetchOne();

        // 사용한 포인트가 있는 경우
        if(usedPoint != null) {

            Integer resultPoint = extPoint - usedPoint.intValue();

            if(resultPoint > 0) {
                // 1의 결과보다 2의 결과가 크면(소멸예정달에 쌓인 포인트가 이때까지 사용한 포인트보다 많은 경우) 소멸 예정 포인트 표시
                return resultPoint;
            } else {
                return 0;
            }
        } else {
            // 그 외의 경우
            return extPoint;
        }
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
