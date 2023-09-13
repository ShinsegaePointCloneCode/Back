package com.example.smilekarina.event.dto;

import lombok.*;

import java.time.LocalDateTime;
@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventGetDto {
    private Long eventId;
    private String eventHead;   //이벤트 제목
    private String linkedUrl;   //이벤트 연결
    private LocalDateTime regDate;
    private LocalDateTime eventResultDate;
    private LocalDateTime eventStart;   //이벤트 시작일
    private LocalDateTime eventEnd; //이벤트 종료
    private String eventType;   //이벤트 종류
}
