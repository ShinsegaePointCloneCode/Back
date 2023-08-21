package com.example.smilekarina.event.application;

import com.example.smilekarina.event.dto.EventGetDto;

public interface EventService {
    void createEvent(EventGetDto eventGetDto);

    EventGetDto getEvent(Long id);
}
