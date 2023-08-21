package com.example.smilekarina.event.dto;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventListGetDto {
    private Long eventId;   //event_id :해당하는 이벤트 id값
    private Boolean prizeBool;  //이벤트 당첨여부
}
