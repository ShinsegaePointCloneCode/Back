package com.example.smilekarina.check.application;



import java.time.LocalDate;
import java.util.List;


public interface CheckService {
    List<Integer> getCheck(String token);
    void createCheck(String token, LocalDate time);
}
