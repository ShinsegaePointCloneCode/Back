package com.example.smilekarina.check.application;



import com.example.smilekarina.check.vo.RouletteCheckOut;

import java.time.LocalDate;
import java.util.List;


public interface CheckService {
    List<Integer> getCheck(String token);
    void createCheck(String token, LocalDate time);
    void createRoulette(String token, Integer point);
    RouletteCheckOut getRoulette(String token);
}
