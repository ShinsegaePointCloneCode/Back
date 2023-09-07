package com.example.smilekarina.gift.dto;

import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftSearchConditionDto {

    private Long userId;
    private String giftGb;
    private Integer page;
    private Integer size;
    private Long offset;

}
