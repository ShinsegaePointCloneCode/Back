package com.example.smilekarina.club.application;

import com.example.smilekarina.club.domain.ClubList;
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


}
