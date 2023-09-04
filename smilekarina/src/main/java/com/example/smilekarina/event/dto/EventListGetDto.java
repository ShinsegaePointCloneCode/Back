package com.example.smilekarina.event.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventListGetDto {
    private Long EventId;
    private String eventHead;   //이벤트 제목
    private String linkedUrl;   //이벤트 연결
    private LocalDateTime reg_date; //이벤트 등록일
    private LocalDateTime eventStart;   //이벤트 시작일
    private LocalDateTime eventEnd; //이벤트 종료일
    private String eventThumbnail;    //이벤트 썸네일
    private String eventType;   //이벤트 종류
}
