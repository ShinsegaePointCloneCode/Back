package com.example.smilekarina.club.application;

import com.example.smilekarina.club.dto.BizGetDto;
import com.example.smilekarina.club.dto.CarGetDto;
import com.example.smilekarina.club.dto.MomGetDto;

public interface ClubService {
    void createClubList(String UUID);
    void createMom(MomGetDto momCreateDto);
    void createCar(CarGetDto carCreateDto);
    void createBiz(BizGetDto bizCreateDto);

}
