package com.example.smilekarina.user.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogInDto {
    String UUID;
    String userName;
    String token;
}
