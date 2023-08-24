package com.example.smilekarina.event.domain;

import com.example.smilekarina.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @Column(name = "linked_url",columnDefinition = "TEXT")
    private String linkedUrl;   //이벤트 연결
    // url이 몇개 들어갈지 모르니까 이걸 어떻게 잘라서 나눌것인가. & 아닌경우는 null
    //todo : varchar 쓸지 TEXT 쓸지 고민해보기

    @Column(nullable = false, name="reg_date")
    private LocalDateTime regDate;
    @Column(nullable = false, name="event_start")
    private LocalDateTime eventStart;   //이벤트 시작일
    @Column(nullable = false, name="event_end")
    private LocalDateTime eventEnd; //이벤트 종료일
    @Column(nullable = false, name="event_thumbnail")
    private Long eventThumbnail;    //이벤트 썸네일
    @Column(nullable = false, name="event_type")
    private String eventType;   //이벤트 종류 - 포인트제공, 응모형, 쿠폰제공, url
    @Column(nullable = false,name="event_benefit",columnDefinition = "TEXT")
    private String eventBenefit; //이벤트 당첨 상품 ex)  포인트 액수, 상품내역, 쿠폰이벤트, url
    @Column(name = "event_result_date")
    private LocalDateTime eventResultDate;    //당첨날짜 - 응모형인경우만, 아닌경우는 null
    @Column(nullable = false, name="event_detail_image")
    private Long eventDetailImage;    //이벤트 세부내용 이미지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private EventPartner eventPartner;  //event_partner_id : 이벤트 주최 id
}
