package com.example.smilekarina.gift.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GiftIn {

    private Integer point;
    private Long imageId;
    private String giftMessage;
    private String pointPassword;
    private String recipientLoginId;

}