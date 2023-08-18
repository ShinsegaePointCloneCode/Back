package com.example.smilekarina.event.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 응모형 이벤트
public class ApplicationEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //응모형 이벤트 id -> application_event_id
    /*
    @Column(nullable = false,name="prize_result_date")
    private LocalDateTime prizeResultDate;  //todo: 당첨 발표일
     */
    @Column(nullable = false, name = "event_id")
    private Long eventId;//이벤트 아이디
    @Column(nullable = false, name="prize_number")
    private Integer prizeNumber;  //당첨인원

}
