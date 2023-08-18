package com.example.smilekarina.point.domain.gift;

import com.example.smilekarina.point.domain.CodeValue;
import lombok.Getter;

@Getter
public enum GiftType implements CodeValue {
    GET("G", "받음"), WAIT("W","대기"), CANCEL("C","취소");

    private final String code;
    private final String value;

    GiftType(String code, String value) {
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
