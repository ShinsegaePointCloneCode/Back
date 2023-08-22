package com.example.smilekarina.club.domain;

import com.example.smilekarina.global.domain.CodeValue;

public enum ClubType implements CodeValue {
    BIZ("B", "BIZ"), CAR("C", "CAR"), MOMKIDS("M", "MOMKIDS"), BEAUTY("T", "BEAUTY");

    private final String code;
    private final String value;

    ClubType(String code, String value) {
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
