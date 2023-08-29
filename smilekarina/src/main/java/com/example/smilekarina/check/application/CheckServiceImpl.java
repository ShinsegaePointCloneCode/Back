package com.example.smilekarina.check.application;


import com.example.smilekarina.check.domain.CheckPoint;
import com.example.smilekarina.check.dto.CheckDateDto;
import com.example.smilekarina.check.dto.CheckPointGetDto;
import com.example.smilekarina.check.infrastructure.CheckRepository;
import com.example.smilekarina.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckServiceImpl implements CheckService {
    private final UserService userService;
    private final CheckRepository checkRepository;

    @Override
    public List<CheckDateDto> getCheck(String token) {
        Long userId = userService.getUserIdFromToken(token);
        LocalDate now = LocalDate.now();
        LocalDate firstDayOfMonth = now.withDayOfMonth(1);
        return checkRepository.findByUserIdAndCkeckDateBetween(userId, firstDayOfMonth, now)
                .stream()
                .map(checkPoint -> new CheckDateDto((long) checkPoint.getCkeckDate().getDayOfMonth()))
                .toList();
    }
//    @Override
//    public void createCheck(String token, LocalDateTime time) {
//        Long userId = userService.getUserIdFromToken(token);
//        Optional<CheckPoint> checkPoint = checkRepository.findLatestByUserId(userId);
//
//    }
}
