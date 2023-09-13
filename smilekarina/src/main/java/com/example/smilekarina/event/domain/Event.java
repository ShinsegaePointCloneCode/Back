package com.example.smilekarina.event.domain;

import com.example.smilekarina.global.domain.BaseEntity;
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
//이벤트
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //event_id
    @Column(nullable = false, length =45, name = "event_head")
    private String eventHead;   //이벤트 제목
    @Column(name = "linked_url")
    private String linkedUrl;   //이벤트 연결
    @Column(nullable = false, name="event_start")
    private LocalDateTime eventStart;   //이벤트 시작일
    @Column(nullable = false, name="event_end")
    private LocalDateTime eventEnd; //이벤트 종료일
    @Column(nullable = false, name="event_thumbnail")
    private String eventThumbnail;    //이벤트 썸네일
    @Column(nullable = false,name="event_benefit",columnDefinition = "TEXT")
    private String eventBenefit; //이벤트 당첨 상품 ex)  포인트 액수, 상품내역, 쿠폰이벤트, url
    @Column(name = "event_result_date")
    private LocalDateTime eventResultDate;    //당첨날짜 - 응모형인경우만, 아닌경우는 null

    @Convert(converter = EventTypeConverter.class)
    private EventType eventType;

    @ManyToOne(fetch = FetchType.LAZY)
    private EventPartner eventPartner;  //event_partner_id : 이벤트 주최 id
}
