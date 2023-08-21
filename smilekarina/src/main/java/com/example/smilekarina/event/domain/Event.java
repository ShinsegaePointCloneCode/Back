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
    @Column(nullable = false, name = "linked_url")  //길이 255
    private String linkedUrl;   //이벤트 연결
    @Column(nullable = false, name="event_start")
    private LocalDateTime eventStart;   //이벤트 시작일
    @Column(nullable = false, name="event_end")
    private LocalDateTime eventEnd; //이벤트 종료일
    @Column(nullable = false, name="event_thumbnail")
    private Long eventThumbnail;    //이벤트 썸네일
    @Column(nullable = false, name="event_type")
    private String eventType;   //이벤트 종류
    @Column(nullable = false, name="event_detail_image")
    private Long eventDetailImage;    //이벤트 세부내용 이미지

    @ManyToOne(fetch = FetchType.LAZY)
    private EventPartner eventPartner;  //event_partner_id : 이벤트 주최 id
}
