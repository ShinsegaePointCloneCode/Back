package com.example.smilekarina.notice.domain;

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
//공지사항
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //공지사항 id
    @Column(nullable = false, length=100,name="notice_titile")
    private String noticeTitle; //공지사항 제목
    @Column(nullable = false,name="notice_content",columnDefinition = "TEXT")
    private String noticeContent;   //공지사항 내용
    /*
    @Column(nullable = false,name="notice_date" )
    private LocalDateTime noticeDate;   //todo : 공지사항 날짜 ->
     */
}
