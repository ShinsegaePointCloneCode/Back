package com.example.smilekarina.agree.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class AgreeTypeConverter implements AttributeConverter<AgreeType, String> {
    @Override
    public String convertToDatabaseColumn(AgreeType attribute) {
        return attribute.getCode();
    }

    @Override
    public AgreeType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(AgreeType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 코드입니다."));
    }
}
