package com.example.smilekarina.point.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointInfoOut {

    private Integer totalPoint;
    private Integer addPoint;
    private Integer extPoint;
    private Integer extNextPoint;

}
