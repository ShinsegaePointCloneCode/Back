package com.example.smilekarina.event.dto;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventParticipateDto {
    Long eventId;
    Boolean prizeBool;
}
