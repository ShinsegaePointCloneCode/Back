package com.example.smilekarina.card.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class IssueTypeConverter implements AttributeConverter<IssueType, String> {
    @Override
    public String convertToDatabaseColumn(IssueType attribute) {
        return attribute.getCode();
    }

    @Override
    public IssueType convertToEntityAttribute(String dbData) {
        return  EnumSet.allOf(IssueType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 코드입니다."));
    }
}
