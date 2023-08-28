package com.example.smilekarina.gift.dto;


import lombok.*;

import java.time.LocalDateTime;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftLastDto {

    private Long giftId;
    private String senderLoginId;
    private String senderName;
    private Integer point;
    private String giftMessage;
    private LocalDateTime createdDate;

}
