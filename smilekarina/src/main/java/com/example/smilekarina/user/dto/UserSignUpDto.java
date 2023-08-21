package com.example.smilekarina.user.dto;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDto {
    private String loginId;
    private String password;
    private String userName;
    private String email;
    private String phone;
    private String address;
}
