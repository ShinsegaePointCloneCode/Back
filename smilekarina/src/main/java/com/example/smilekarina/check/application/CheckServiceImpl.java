package com.example.smilekarina.check.application;


import com.example.smilekarina.check.domain.CheckPoint;
import com.example.smilekarina.check.dto.CheckPointGetDto;
import com.example.smilekarina.check.infrastructure.CheckRepository;
import com.example.smilekarina.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckServiceImpl implements CheckService {
    private final UserService userService;
    private final CheckRepository checkRepository;
    @Override
    public void createCheck(String token, LocalDateTime time) {
        Long userId = userService.getUserIdFromToken(token);
        Optional<CheckPoint> checkPoint = checkRepository.findLatestByUserId(userId);

    }
}
