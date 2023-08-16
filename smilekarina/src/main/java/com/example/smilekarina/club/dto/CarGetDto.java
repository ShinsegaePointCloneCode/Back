package com.example.smilekarina.club.dto;


import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarGetDto {
    private String regionNumber;
    private Integer carNumber;
}
