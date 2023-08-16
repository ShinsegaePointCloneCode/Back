package com.example.smilekarina.notice.domain;

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
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length=100,name="notice_titile")
    private String noticeTitle;
    @Column(nullable = false,name="notice_content",columnDefinition = "TEXT")
    private String noticeContent;
    @Column(nullable = false,name="notice_date",columnDefinition ="int default 0" )
    private Integer noticeDate;

}
