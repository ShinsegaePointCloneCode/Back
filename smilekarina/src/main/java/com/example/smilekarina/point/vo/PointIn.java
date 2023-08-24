package com.example.smilekarina.point.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PointIn {

    private Integer point;
    private String pointType;
    private Boolean used;
    private Long userId;

}
