package com.example.smilekarina.check.application;


import java.time.LocalDateTime;

public interface CheckService {
    void createCheck(String token, LocalDateTime time);
}
