//package com.example.smilekarina.club.domain;
//
//import jakarta.persistence.AttributeConverter;
//import java.util.EnumSet;
//import java.util.NoSuchElementException;
//
//public class ClubTypeConverter implements AttributeConverter<ClubType, String> {
//    @Override
//    public String convertToDatabaseColumn(ClubType attribute) {
//        return attribute.getCode();
//    }
//
//    @Override
//    public ClubType convertToEntityAttribute(String dbData) {
//        return EnumSet.allOf(ClubType.class).stream()
//                .filter(c -> c.getCode().equals(dbData))
//                .findFirst()
//                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 코드입니다."));
//    }
//}
