package com.example.smilekarina.event.application;

import com.example.smilekarina.event.dto.*;
import com.example.smilekarina.event.vo.EventListOut;

import java.util.List;

public interface EventService {
    //void createEvent(CreateEventDto createEventDto);
    //void createEventPartner(EventPartnerGetDto eventPartnerGetDto);
    EventGetDto getEvent(Long id);
    EventPrizeDto getPrizeEvent(Long id);
    List<EventListGetDto> getAllEvent();
    List<EventListOut> checkIngEvent(Integer orderType,Integer pageNo,Integer size);
    EventGetDto checkEvent(Integer eventNo);
}
