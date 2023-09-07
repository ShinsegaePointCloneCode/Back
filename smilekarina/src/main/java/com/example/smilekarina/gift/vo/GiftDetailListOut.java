package com.example.smilekarina.gift.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiftDetailListOut {

    private Integer point;
    private Long pointId;
    private LocalDateTime updatedDate;
    private String giftType;
    private Boolean messageOnOff;
    private Long giftId;
    private String otherName;
    private String otherId;

}
