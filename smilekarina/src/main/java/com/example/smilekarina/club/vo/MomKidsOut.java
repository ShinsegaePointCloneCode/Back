package com.example.smilekarina.club.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MomKidsOut {
    private String sexFirst;
    private LocalDate birthFirst;
    private String sexSecond;
    private LocalDate birthSecond;
}
