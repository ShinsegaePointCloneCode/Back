package com.example.smilekarina.event.domain;

import com.example.smilekarina.global.domain.CodeValue;
import lombok.Getter;

@Getter
public enum EventType implements CodeValue{
    POINT("PO","POINT"),
    PARTICIPATE("PA","PARTICIPATE"),
    COUPON("CO","COUPON");

    private final String code ;
    private final String value;


    EventType(String code,String value){
        this.code=code;
        this.value=value;
    }

    @Override
    public String getCode(){return code;}

    @Override
    public String getValue(){return value;}
}
