package com.example.smilekarina.event.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//포인트 제공 이벤트
public class PointEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //PointOfferId
    @Column(nullable = false, name="point_price")
    private Integer pointPrice; // 포인트제공액
    @Column(nullable = false,name="event_id")
    private Long eventId;   // 이벤트아이디..? todo : 이벤트 id가 뭔지 앱켜고 확인해보기
}
