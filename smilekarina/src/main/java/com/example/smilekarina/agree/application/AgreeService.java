package com.example.smilekarina.agree.application;

import com.example.smilekarina.agree.dto.AgreeAdvertiseDto;
import com.example.smilekarina.agree.vo.AgreeAdvertiseOut;


public interface AgreeService {
    void createAgreeAdvertise(String token, AgreeAdvertiseDto agreeAdvertiseDto);
    AgreeAdvertiseOut getAgreeAdvertise(String token);
}
