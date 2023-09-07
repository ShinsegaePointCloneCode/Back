package com.example.smilekarina.gift.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiftMessageOut {

    private String giftMessage;
    private String giftImage;

}
