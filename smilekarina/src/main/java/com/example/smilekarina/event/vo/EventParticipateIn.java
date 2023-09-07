package com.example.smilekarina.event.vo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventParticipateIn {
    Long eventId;
    Boolean prize_bool;
}
