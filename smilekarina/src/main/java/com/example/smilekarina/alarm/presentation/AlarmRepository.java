package com.example.smilekarina.alarm.presentation;

import com.example.smilekarina.alarm.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
