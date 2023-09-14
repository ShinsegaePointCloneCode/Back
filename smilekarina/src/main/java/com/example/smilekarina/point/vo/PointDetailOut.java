package com.example.smilekarina.point.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointDetailOut {

    private Long pointId;
    private Integer point;
    private String used;
    private String pointType;
    private LocalDateTime showDate;

}
