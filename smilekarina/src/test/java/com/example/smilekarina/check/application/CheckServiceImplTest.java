package com.example.smilekarina.check.application;

import com.example.smilekarina.check.domain.CheckPoint;
import com.example.smilekarina.check.dto.CheckDateDto;
import com.example.smilekarina.check.infrastructure.CheckRepository;
import com.example.smilekarina.check.vo.CreateCheckIn;
import com.example.smilekarina.user.application.UserService;
import com.example.smilekarina.user.application.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CheckServiceImplTest {
    @InjectMocks
    private CheckServiceImpl checkService;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private CheckRepository checkRepository;
    @Test
    void getCheck() {
        // given
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI3ZW9tMTMiLCJpYXQiOjE2OTMzMDg4MDIsImV4cCI6MTY5MzMyMzIwMn0.4r40CMQxOvYkdbw0RHMeID5Hb-xcdPfqpZLzgrFOAFI";
        Long userId = 1L;
        LocalDate now = LocalDate.now();
        LocalDate firstDayOfMonth = now.withDayOfMonth(1);

        List<CheckPoint> mockResult = List.of(CheckPoint.builder().cntDate(1).checkDate(now).userId(userId).build()); // 예시로, 실제 객체 구성 필요
        doReturn(userId).when(userService).getUserIdFromToken(token);
        doReturn(mockResult).when(checkRepository).findByUserIdAndCheckDateBetween(userId, firstDayOfMonth, now);
        // when
        List<Integer> result = checkService.getCheck(token);
        // then
        assertEquals(mockResult.size(), result.size());
        // verify
    }
}