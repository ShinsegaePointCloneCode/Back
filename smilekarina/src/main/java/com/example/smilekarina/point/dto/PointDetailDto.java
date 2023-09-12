package com.example.smilekarina.point.dto;

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
public class PointDetailDto {

    private Long pointId;
    private Integer point;
    private Boolean used;
    private PointType pointType;
    private LocalDateTime createdDate;

}
