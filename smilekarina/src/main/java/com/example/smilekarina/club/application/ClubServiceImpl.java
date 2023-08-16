package com.example.smilekarina.club.application;

import com.example.smilekarina.club.domain.ClubList;
import com.example.smilekarina.club.dto.BizGetDto;
import com.example.smilekarina.club.dto.CarGetDto;
import com.example.smilekarina.club.dto.MomGetDto;
import com.example.smilekarina.club.infrastructure.ClubListRepository;
import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClubServiceImpl implements ClubService{
    private final UserRepository userRepository;
    private final ClubListRepository clubListRepository;
    @Override
    public void createClubList(String UUID) {
        User getUser = userRepository.findByUUID(UUID); // todo: UUID 유효성 검사
        ClubList clubList = ClubList.builder()
                .user(getUser)
                .beauty(false)
                .momKids(false)
                .car(false)
                .biz(false)
                .build();
        clubListRepository.save(clubList);
    }

    @Override
    public void createMom(MomGetDto momCreateDto) {

    }

    @Override
    public void createCar(CarGetDto carCreateDto) {

    }

    @Override
    public void createBiz(BizGetDto bizCreateDto) {

    }
}
