package com.example.smilekarina.club.dto;

import lombok.*;

import java.time.LocalDate;
@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MomGetDto {
    private Boolean sexFirst;
    private LocalDate birthFirst;
    private Boolean sexSecond;
    private LocalDate birthSecond;

}
