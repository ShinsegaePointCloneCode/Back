package com.example.smilekarina.club.application;

import com.example.smilekarina.club.domain.ClubType;
import com.example.smilekarina.club.vo.*;
import com.example.smilekarina.global.vo.ResponseOut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface ClubService {
    void registerClubForMomKids(String token, MomKidsIn momKidsIn);
    void registerClubForBeauty(String token);
    void registerClubForCar(String token, CarIn carIn);
    void registerClubForBiz(String token,BizIn bizIn);
    void createClubList(Long userId,Long clubId);
    void clear(String token, ClubType clubType);
    MomKidsOut getMomKidsData(String token);
    BizOut getBizData(String token);
    CarOut getCarData(String token);
    AllStateOut getClubState(String token);
}
