package com.example.smilekarina.event.application;

import com.example.smilekarina.event.dto.*;
import com.example.smilekarina.event.vo.EventListOut;
import com.example.smilekarina.event.vo.EventOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    //void createEvent(CreateEventDto createEventDto);
    EventOut getEvent(Long eventNo);
    EventGetDto myEvent(EventGetDto eventGetDto);
    //EventPrizeDto getPrizeEvent(Long id);
    List<EventListGetDto> getAllEvent();
    //EventGetDto checkEvent(Integer eventNo);
    List<EventListOut> endEvent(Integer pageNo,Integer size);
    List<EventListOut> myEventList(Integer pageNo,Integer size);
    Page<EventListOut> checkIngEvent(Integer orderType, Pageable pageable);
}

