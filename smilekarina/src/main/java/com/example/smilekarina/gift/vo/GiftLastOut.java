package com.example.smilekarina.gift.vo;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiftLastOut {

    private Long giftId;
    private String senderLoginId;
    private String senderName;
    private Integer point;
    private String giftMessage;
    private LocalDateTime createdDate;

}
