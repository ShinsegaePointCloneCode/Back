package com.example.smilekarina.point.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointPasswordCheckDto {

    private Long userId;
    private String pointPassword;

}
