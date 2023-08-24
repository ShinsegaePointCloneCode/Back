package com.example.smilekarina.point.dto;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointAddDto {
    private Integer point;
    private String pointType;
    private Boolean used;
    private Long userId;
}
