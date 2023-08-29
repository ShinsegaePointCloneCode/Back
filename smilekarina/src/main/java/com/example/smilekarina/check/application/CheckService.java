package com.example.smilekarina.check.application;


import com.example.smilekarina.check.domain.CheckPoint;
import com.example.smilekarina.check.dto.CheckDateDto;

import java.util.List;
import java.util.Optional;

public interface CheckService {
    List<CheckDateDto> getCheck(String token);
//    void createCheck(String token, LocalDateTime time);
}
