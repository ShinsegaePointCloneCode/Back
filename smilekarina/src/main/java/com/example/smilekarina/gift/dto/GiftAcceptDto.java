package com.example.smilekarina.gift.dto;

import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftAcceptDto {

    private Long giftId;
    private String token;

}
