package com.example.smilekarina.event.application;

import com.example.smilekarina.event.domain.Event;
import com.example.smilekarina.event.domain.EventPartner;
import com.example.smilekarina.event.dto.*;
import com.example.smilekarina.event.infrastructure.EventPartnerRepository;
import com.example.smilekarina.event.infrastructure.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService{
    /*
    private final PointEventRepository pointEventRepository;

    public PointEventRepository getPointEventRepository() {
        return pointEventRepository.findById();
    }
     */
    private final EventRepository eventRepository;
    private final EventPartnerRepository eventPartnerRepository;
    //관리자 입장에서 생성
    @Override
    public void createEvent(CreateEventDto createEventDto){
        eventRepository.save(Event.builder()
                .eventHead(createEventDto.getEventHead())
                .linkedUrl(createEventDto.getLinkedUrl())
                .regDate(createEventDto.getRegDate())
                .eventStart(createEventDto.getEventStart())
                .eventEnd(createEventDto.getEventEnd())
                .eventThumbnail(createEventDto.getEventThumbnail())
                .eventType(createEventDto.getEventType())
                .eventBenefit(createEventDto.getEventBenefit())
                .eventResultDate(createEventDto.getEventResultDate())
                .eventDetailImage(createEventDto.getEventDetailImage())
                .build());
    }
    //이벤트 주체사
    @Override
    public void createEventPartner(EventPartnerGetDto eventPartnerGetDto){
        eventPartnerRepository.save(EventPartner.builder()
                .eventPartnerName(eventPartnerGetDto.getEventPartnerName())
                .build());
    }

    //이벤트게시글 보기(일반)
    @Override
    public EventGetDto getEvent(Long id){
        Event event=eventRepository.findById(id).get();
        log.info("{}",event);
        ModelMapper mapper=new ModelMapper();
        EventGetDto eventGetDto = mapper.map(event, EventGetDto.class);
        log.info("{}", eventGetDto);
        return eventGetDto;
    }

    @Override
    public EventPrizeDto getPrizeEvent(Long id){
        Event event=eventRepository.findById(id).get();
        log.info("{}",event);
        ModelMapper mapper=new ModelMapper();
        EventPrizeDto eventPrizeDto = mapper.map(event, EventPrizeDto.class);
        log.info("{}", eventPrizeDto);
        return eventPrizeDto;
    }

    //이벤트리스트로 보기
    @Override
    public List<EventListGetDto> getAllEvent() {
        List<Event> eventlist=eventRepository.findAll();
        //Event에서 EventGetDto로 넣어주는 처리
        //USer Point에 Sample 찾기
        List<EventListGetDto> eventListGetDtoList = eventlist.stream().map(event->{
            return EventListGetDto.builder()
                    .eventHead(event.getEventHead())
                    .linkedUrl(event.getLinkedUrl())
                    .reg_date(event.getRegDate())
                    .eventStart(event.getEventStart())
                    .eventEnd(event.getEventEnd())
                    .eventThumbnail(event.getEventThumbnail())
                    .eventType(event.getEventType())
                    .build();
        }
        ).toList();
        log.info("Eventlist is : {}" , eventlist);
        return eventListGetDtoList;
    }


}