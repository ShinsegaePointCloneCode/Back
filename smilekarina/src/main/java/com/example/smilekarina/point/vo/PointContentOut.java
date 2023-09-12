package com.example.smilekarina.point.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointContentOut {

    private String franchiseName;
    private String addDetailDesc;
    private String receiptNumber;
    private Boolean messageOnOff;
    private String giftType;
    private String otherName;
    private String otherId;
    private Long giftId;

}
