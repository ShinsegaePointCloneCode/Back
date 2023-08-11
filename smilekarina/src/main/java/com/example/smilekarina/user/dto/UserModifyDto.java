package com.example.smilekarina.user.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserModifyDto {
    private String address;
    private String email;
}
