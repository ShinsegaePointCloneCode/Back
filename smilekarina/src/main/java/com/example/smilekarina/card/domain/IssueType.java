package com.example.smilekarina.card.domain;

import com.example.smilekarina.global.domain.CodeValue;
import lombok.Getter;

@Getter
public enum IssueType implements CodeValue {
    ONLINE("ON","온라인"), OFFLINE("OF","오프라인"), PARTNERSHIP("PA","제휴");

    private final String code;
    private final String value;

    IssueType(String code, String value) {
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
