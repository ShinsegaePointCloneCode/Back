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

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class PointServiceImple implements PointService{
    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    @Override
    @Convert(converter = PointTypeConverter.class)
    public void createPoint(PointAddDto pointAddDto) {

        User getUser = userRepository.findByLoginId(pointAddDto.getLoginId());
        log.info("user is : {}" , getUser);
        if(getUser == null){
            log.info("user is null");
            return;
        }

        PointType pointType = new PointTypeConverter().convertToEntityAttribute(pointAddDto.getPointType());

        //todo TotalPoint 계산

        pointRepository.save(Point.builder()
                .point(pointAddDto.getPoint())
                .totalPoint(pointAddDto.getPoint())
                .pointType(pointType)
                .user(getUser)
                .used(pointAddDto.getUsed())
                .build());
    }

    @Override
    @Convert(converter = PointTypeConverter.class)
    public List<PointGetDto> getPointByUser(Long userId) {
        List<Point> pointList = pointRepository.findByUserId(userId);
        List<PointGetDto> pointGetDtoList = pointList.stream().map(point -> {
                    PointType pointType = new PointTypeConverter().convertToEntityAttribute(point.getPointType().getCode());
                    return PointGetDto.builder()
                            .pointType(pointType.getValue())
                            .point(point.getPoint())
                            .used(point.getUsed())
                            .build();
                }
        ).toList();
        log.info("pointList is : {}" , pointList);
        return pointGetDtoList;
    }
}
