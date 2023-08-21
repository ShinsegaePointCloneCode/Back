package com.example.smilekarina.notice.domain;


import com.example.smilekarina.global.domain.BaseEntity;
import com.example.smilekarina.user.domain.User;
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
//1:1 상담-> 최대 6개월까지 보관
//todo: 6개월 이후엔 화면에서만 안보이게 하는것인지 아니면 db에서도 없어지는것인지는 알아보기
public class Report extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //1:1 상담 아이디
    // 답변이 달리기 전까진 대분류 소분류 제목 내용 수정이 가능
    // 답변이 달리면 user는 수정 X
    @Column(nullable = false, length = 30, name = "big_option")
    private String bigOption;   //대분류
    @Column(nullable = false, length = 30, name = "small_option")
    private String smallOption; //소분류
    @Column(nullable = false, length = 100, name="report_title")
    private String reportTitle; //제목
    @Column(nullable = false, name = "report_content",columnDefinition = "TEXT")
    private String reportContent;   //내용
    @Column(nullable = true, length = 255, name="answer_title")
    private String answerTitle; // 답변 제목
    @Column(nullable = true, name="answer_content", columnDefinition = "TEXT")
    private String answerContent;   //답변 내용

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;  //user id
}
