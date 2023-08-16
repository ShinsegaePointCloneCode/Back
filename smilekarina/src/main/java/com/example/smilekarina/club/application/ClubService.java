package com.example.smilekarina.club.application;

import com.example.smilekarina.club.dto.BizCreateDto;
import com.example.smilekarina.club.dto.CarCreateDto;
import com.example.smilekarina.club.dto.ClubListCreateDto;
import com.example.smilekarina.club.dto.MomCreateDto;
import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.dto.UserSignUpDto;

public interface ClubService {
    void createClubList(User user);
    void createMom(MomCreateDto momCreateDto);
    void createCar(CarCreateDto carCreateDto);
    void createBiz(BizCreateDto bizCreateDto);

}
