package com.example.smilekarina.club.dto;


import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarCreateDto {
    private String regionNumber;
    private Integer carNumber;
}
