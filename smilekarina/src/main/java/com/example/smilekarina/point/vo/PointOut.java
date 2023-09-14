package com.example.smilekarina.point.vo;

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
