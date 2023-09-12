package com.example.smilekarina.point.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointListOut {

    private Long aTotalPoint;
    private Long uTotalPoint;
    private List<PointDetailOut> pointDetailOutList;
    private Integer page;
    private Integer size;
    private Long totalRows;
}
