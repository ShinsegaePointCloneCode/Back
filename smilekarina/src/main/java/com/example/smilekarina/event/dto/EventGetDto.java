package com.example.smilekarina.event.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventGetDto {
    private String eventHead;   //이벤트 제목
    private String linkedUrl;   //이벤트 연결
    private LocalDateTime eventStart;   //이벤트 시작일
    private LocalDateTime eventEnd; //이벤트 종료일
    private Long eventThumbnail;    //이벤트 썸네일
    private String eventType;   //이벤트 종류
    private Long eventDetailImage;    //이벤트 세부내용 이미지
}
