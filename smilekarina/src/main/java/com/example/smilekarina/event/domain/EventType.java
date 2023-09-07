package com.example.smilekarina.event.domain;

import com.example.smilekarina.global.domain.CodeValue;
import lombok.Getter;

@Getter
public enum EventType implements CodeValue{
    POINT("PO","POINT"), // 포인트 제공
    PARTICIPATE("PA","PARTICIPATE"), // 당첨
    COUPON("CO","COUPON"), // 쿠폰 발행
    URL("UR","URL"); // 다른 페이지로 이동

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
