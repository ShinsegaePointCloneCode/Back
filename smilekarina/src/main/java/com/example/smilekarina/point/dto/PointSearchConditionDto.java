package com.example.smilekarina.point.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointSearchConditionDto {

    private String token;
    private String pointType;
    private String rangeStartDate;
    private String rangeEndDate;
    private String usedType;
    private String pointHistoryType;
    private Integer page;
    private Integer size;
    private Long offset;

}
