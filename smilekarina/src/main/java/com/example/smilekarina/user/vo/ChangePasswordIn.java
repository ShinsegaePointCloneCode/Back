package com.example.smilekarina.user.vo;

import lombok.Getter;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
public class ChangePasswordIn {
    private String loginId;
    private String password;
}
