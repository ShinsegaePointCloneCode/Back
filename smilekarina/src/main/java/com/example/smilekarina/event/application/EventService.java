package com.example.smilekarina.event.application;

import com.example.smilekarina.event.dto.*;

import java.util.List;

public interface EventService {
    void createEvent(CreateEventDto createEventDto);
    void createEventPartner(EventPartnerGetDto eventPartnerGetDto);
    EventGetDto getEvent(Long id);
    EventPrizeDto getPrizeEvent(Long id);
    List<EventListGetDto> getAllEvent();
}
