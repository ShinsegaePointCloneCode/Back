package com.example.smilekarina.event.application;

import com.example.smilekarina.event.dto.CreateEventDto;
import com.example.smilekarina.event.dto.EventGetDto;
import com.example.smilekarina.event.dto.EventListGetDto;
import com.example.smilekarina.event.dto.EventPartnerGetDto;

import java.util.List;

public interface EventService {
    void createEvent(CreateEventDto createEventDto);
    void createEventPartner(EventPartnerGetDto eventPartnerGetDto);
    EventGetDto getEvent(Long id);

    List<EventListGetDto> getAllEvent();
}
