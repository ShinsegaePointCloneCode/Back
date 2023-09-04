package com.example.smilekarina.agree.domain;

import com.example.smilekarina.global.domain.CodeValue;
import lombok.Getter;
@Getter
public enum AgreeType implements CodeValue {
    OMNISERVICE("O","옴니서비스위한개인정보제공동의"),
    EMART("E","이마트사이트이용약관"),
    SHINSEGAE("S","신세계백화점사이트이용약관");
    private final String code;
    private final String value;

    AgreeType(String code, String value) {
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
