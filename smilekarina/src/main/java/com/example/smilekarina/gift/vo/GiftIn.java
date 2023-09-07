package com.example.smilekarina.gift.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GiftIn {

    private Integer point;
    private String giftImage;
    private String giftMessage;
    private String pointPassword;
    private String recipientLoginId;
    private String recipientName;

}
