package com.example.smilekarina.event.vo;

import com.example.smilekarina.event.domain.EventType;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class   EventListOut {
    private Long id;
    private String eventHead;   //이벤트 제목
    private String linkedUrl;   //디테일페이지 연결링크
    private LocalDateTime regDate;//이벤트 등록일
    private LocalDateTime eventStart;   //이벤트 시작일
    private LocalDateTime eventEnd; //이벤트 종료일
    private String eventThumbnail;    //이벤트 썸네일
    private String eventType;   //이벤트 종류
    public EventListOut(Long id, String eventHead, String linkedUrl, LocalDateTime regDate,
                        LocalDateTime eventStart, LocalDateTime eventEnd, String eventThumbnail,
                        String eventType) {
        this.id = id;
        this.eventHead = eventHead;
        this.linkedUrl = linkedUrl;
        this.regDate = regDate;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eventThumbnail = eventThumbnail;
        this.eventType = eventType;
    }
}
