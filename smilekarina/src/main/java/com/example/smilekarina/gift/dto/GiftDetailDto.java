package com.example.smilekarina.gift.dto;

import com.example.smilekarina.gift.domain.GiftType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiftDetailDto {

    private Long giftId;
    private Integer point;
    private GiftType giftType;
    private String giftMessage;
    private Long giftSenderId;
    private Long senderPointId;
    private Long giftRecipientId;
    private Long resultPointId;
    private LocalDateTime updatedDate;

}
