package com.example.smilekarina.point.domain;

import com.example.smilekarina.global.domain.CodeValue;
import lombok.Getter;

@Getter
public enum PointType implements CodeValue {
    SMARTRECEIPT("SM","스마트영수증적립"),
    CONVERT("CO","전환"),
    ACCEPT("AC","적립"),
    EVENT("EV", "이벤트"),
    GIFT("GF", "선물"),
    CHECK("CH","출석"),
    ROULETTE("RO","룰렛");

    private final String code;
    private final String value;

    PointType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
