package com.example.smilekarina.point.vo;

import com.example.smilekarina.point.domain.PointType;
import lombok.Data;

@Data
public class PointIn {
    private Integer point;
    private String pointType;
    private Boolean used;
    private String loginId;
}
