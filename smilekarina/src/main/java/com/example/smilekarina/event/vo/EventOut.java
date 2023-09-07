package com.example.smilekarina.event.vo;

import com.example.smilekarina.event.domain.Event;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventOut {

    private Long eventId;
    private String eventHead;   //이벤트 제목
    private String linkedUrl;   //이벤트 연결
    private LocalDateTime regDate;//이벤트 등록일
    private LocalDateTime eventResultDate;
    private LocalDateTime eventStart;   //이벤트 시작일
    private LocalDateTime eventEnd; //이벤트 종료일
    private String eventType;   //이벤트 종류
    private List<String> eventDetailImage;

    /*
    public EventOut(Event event){
        this.eventId=event.getId();
        this.eventHead=event.getEventHead();
        this.linkedUrl=event.getLinkedUrl();
        this.regDate=event.getCreatedDate();
        this.eventResultDate=event.getEventResultDate();
        this.eventStart=event.getEventStart();
        this.eventEnd=event.getEventEnd();
        this.
    }

     */
}
