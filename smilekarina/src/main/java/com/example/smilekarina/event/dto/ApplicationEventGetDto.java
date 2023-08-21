package com.example.smilekarina.event.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationEventGetDto {
    private LocalDateTime prizeResultDate;//당첨 발표일
    private Long eventId;//이벤트 아이디
    private Integer prizeNumber;  //당첨인원
}
