package com.example.smilekarina.club.application;

import com.example.smilekarina.club.domain.ClubList;
import com.example.smilekarina.club.dto.BizCreateDto;
import com.example.smilekarina.club.dto.CarCreateDto;
import com.example.smilekarina.club.dto.ClubListCreateDto;
import com.example.smilekarina.club.dto.MomCreateDto;
import com.example.smilekarina.club.infrastructure.ClubListRepository;
import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.dto.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClubServiceImpl implements ClubService{
    private final ClubListRepository clubListRepository;
    @Override
    public void createClubList(User user) {
        ClubList clubList = ClubList.builder()
                .user(user)
                .beauty(false)
                .momKids(false)
                .car(false)
                .biz(false)
                .build();
        clubListRepository.save(clubList);
    }

    @Override
    public void createMom(MomCreateDto momCreateDto) {

    }

    @Override
    public void createCar(CarCreateDto carCreateDto) {

    }

    @Override
    public void createBiz(BizCreateDto bizCreateDto) {

    }
}
