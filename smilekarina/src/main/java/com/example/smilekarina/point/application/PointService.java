package com.example.smilekarina.point.application;

import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.point.dto.PointGetDto;

import java.util.List;

public interface PointService {
    void createPoint(PointAddDto pointAddDto);
    List<PointGetDto> getPointByUser(Long userId);
}
