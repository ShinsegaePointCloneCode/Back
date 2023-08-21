package com.example.smilekarina.notice.dto;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeGetDto {
    private String noticeTitle; //공지사항 제목
    private String noticeContent;   //공지사항 내용
}
