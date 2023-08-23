package com.example.smilekarina.club.vo;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MomKidsIn {
    private String sexFirst;
    private LocalDate birthFirst;
    private String sexSecond;
    private LocalDate birthSecond;
}
