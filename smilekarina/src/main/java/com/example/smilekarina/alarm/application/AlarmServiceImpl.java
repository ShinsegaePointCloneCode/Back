package com.example.smilekarina.alarm.application;

import com.example.smilekarina.alarm.presentation.AlarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
//@Transactional(readOnly = true)
public class AlarmServiceImpl implements AlarmService{
    private final AlarmRepository alarmRepository;

}
