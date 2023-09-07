package com.example.smilekarina.event.domain;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDetailImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //상세이미지id
    @Column(nullable = false, name="event_detail_image")
    private String eventDetailImage;
    @Column(nullable = false, name="event_id")
    private Long eventId;
}
