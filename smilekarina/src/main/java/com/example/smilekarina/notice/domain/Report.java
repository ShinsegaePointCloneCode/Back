package com.example.smilekarina.notice.domain;


import com.example.smilekarina.user.domain.User;
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
//1:1 상담
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //1:1 상담 아이디
    @Column(nullable = false, length = 30, name = "big_option")
    private String bigOption;   //대분류
    @Column(nullable = false, length = 30, name = "small_option")
    private String smallOption; //소분류
    @Column(nullable = false, length = 100, name="report_title")
    private String reportTitle; //제목
    @Column(nullable = false, name = "report_content",columnDefinition = "TEXT")
    private String reportContent;   //내용
    /*
    @Column (nullable = false, name="report_date")
    private LocalDateTime reportDate;   // todo :상담 등록 날짜: 년 월 일
     */
    @Column(nullable = false, length = 255, name="answer_title")
    private String answerTitle; // 답변 제목
    @Column(nullable = false, name="answer_content", columnDefinition = "TEXT")
    private String answerContent;   //답변 내용
    /*
    @Column(nullable = false,name="answer_date")
    private LocalDateTime answerDate; // todo : 답변등록 날짜: 년 월 일 시 분 초
     */

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;  //user id
}
