package com.example.smilekarina.point.domain;

import com.example.smilekarina.global.domain.CodeValue;
import lombok.Getter;

@Getter
public enum PointType implements CodeValue {
    SMARTRECEIPT("SM","스마트영수증"),
    CONVERT("CO","전환"),
    GENERAL("GE","일반"),
    EVENT("EV", "이벤트"),
    GIFT("GF", "선물"),
    CHECK("CH","출석"),
    ROULETTE("RO","룰렛"),
    CANCELGIFT("CA","선물사용취소"),
    EXTINCTION ("EX", "소멸");

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
