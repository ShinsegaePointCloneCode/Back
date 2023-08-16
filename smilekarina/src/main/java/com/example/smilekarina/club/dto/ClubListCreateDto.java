package com.example.smilekarina.club.dto;

import jakarta.persistence.Column;
import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClubListCreateDto {
    private Boolean beauty;
    private Boolean momKids;
    private Boolean car;
    private Boolean biz;
}
