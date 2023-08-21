package com.example.smilekarina.event.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 이벤트 적립 포인트
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventAcceptPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "point_id")
    private Long pointId;

    @Column(nullable = false, name = "point_event_id")
    private Long pointEventId;

    @Column(nullable = false, name = "user_id")
    private Long userId;

}
