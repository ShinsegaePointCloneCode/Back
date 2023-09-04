package com.example.smilekarina.event.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class EventTypeConverter implements AttributeConverter<EventType,String> {
    @Override
    public String convertToDatabaseColumn(EventType attribute) {
        return attribute.getCode();
    }

    @Override
    public EventType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(EventType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 코드입니다."));}}
