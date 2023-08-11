package com.example.smilekarina.point.vo;

import com.example.smilekarina.point.domain.PointType;
import com.example.smilekarina.user.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointOut {
    private Integer point;
    private Integer totalPoint;
    private String pointType;
    private Boolean used;
}
