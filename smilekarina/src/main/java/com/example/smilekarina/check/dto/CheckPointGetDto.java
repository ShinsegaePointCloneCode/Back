package com.example.smilekarina.check.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckPointGetDto {
    private Integer userId;
    private Integer cntDate;
    private LocalDate ckeckDate;
}
