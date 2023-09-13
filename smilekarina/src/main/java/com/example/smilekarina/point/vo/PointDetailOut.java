package com.example.smilekarina.point.vo;

import com.example.smilekarina.point.domain.PointType;
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
    private Boolean used;
    private String pointType;
    private LocalDateTime showDate;

}
