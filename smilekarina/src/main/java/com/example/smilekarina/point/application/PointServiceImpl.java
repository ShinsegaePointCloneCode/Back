package com.example.smilekarina.point.application;

import com.example.smilekarina.point.domain.Point;
import com.example.smilekarina.point.domain.PointType;
import com.example.smilekarina.point.domain.PointTypeConverter;
import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.point.dto.PointGetDto;
import com.example.smilekarina.point.infrastructure.PointRepository;
import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.infrastructure.UserRepository;
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointServiceImpl implements PointService{

    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    @Override
    public Integer getUsablePoint(String UUID) {

//        // 포인트를 조회하기 위해 유저정보(객체)를 가져오기
//        Optional<User> user = userRepository.findByUUID(UUID);
//
//        if(!user.isPresent()) {
//            throw new IllegalArgumentException();
//        }
//
//        Optional<Point> point = pointRepository.findFirstByUserAndCreatedDateBeforeOrderByIdDesc(user.get(), LocalDateTime.now());
//
//        if(!point.isPresent()) {
//            throw new IllegalArgumentException();
//        }
//
//        return point.get().getTotalPoint();
        return null;
    }

    /*
        포인트 생성
     */
    @Override
    public Long registerPoint(PointAddDto pointAddDto) {

        User user = userRepository.findById(pointAddDto.getUserId()).orElseThrow(IllegalArgumentException::new);

        // TotalPoint 계산을 위해 가장 최신 포인트 정보 가져 오기
        Point lastPoint = pointRepository.findFirstByUserOrderByIdDesc(user);

        Integer totalPoint = 0;
        if(lastPoint != null) {
            totalPoint = lastPoint.getTotalPoint() + pointAddDto.getPoint();
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
