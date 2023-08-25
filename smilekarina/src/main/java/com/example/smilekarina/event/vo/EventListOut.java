package com.example.smilekarina.event.vo;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventListOut {
    private Integer rowNum;
    private Long eventId;
    private String eventHead;   //이벤트 제목
    private String linkedUrl;   //디테일페이지 연결링크
    private LocalDateTime reg_date;//이벤트 등록일
    private LocalDateTime eventStart;   //이벤트 시작일
    private LocalDateTime eventEnd; //이벤트 종료일
    private Long eventDetailImage;    //이벤트 썸네일
    private String eventType;   //이벤트 종류
}
