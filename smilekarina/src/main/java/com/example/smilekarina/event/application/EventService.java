package com.example.smilekarina.event.application;

import com.example.smilekarina.event.dto.*;
import com.example.smilekarina.event.vo.EventListOut;
import com.example.smilekarina.event.vo.EventOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    EventOut getEvent(Long eventNo);
    Page<EventListOut> endEvent(Pageable pageable);
    Page<EventListOut> checkIngEvent(Integer orderType, Pageable pageable);

    Page<EventListOut> prizeEvent(Pageable pageable);

    void createMyEvent(String token, EventListGetDto map);

    Page<EventListOut> myPAEventList(String token, Pageable pageable);

    Page<EventListOut> myWinEvent(String token, Pageable pageable);
}

