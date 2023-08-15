package com.example.smilekarina.alarm.application;

import com.example.smilekarina.alarm.presentation.AlarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlarmServiceImple implements AlarmService{
    private final AlarmRepository alarmRepository;

}
