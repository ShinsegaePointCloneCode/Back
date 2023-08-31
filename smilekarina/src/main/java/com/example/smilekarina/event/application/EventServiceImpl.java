package com.example.smilekarina.event.application;

import com.example.smilekarina.event.domain.Event;
import com.example.smilekarina.event.domain.EventType;
import com.example.smilekarina.event.domain.EventTypeConverter;
import com.example.smilekarina.event.dto.*;
import com.example.smilekarina.event.infrastructure.EventPartnerRepository;
import com.example.smilekarina.event.infrastructure.EventRepository;
import com.example.smilekarina.event.vo.EventListOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;
    //private final EventPartnerRepository eventPartnerRepository;
    //관리자 입장에서 생성

    @Override
    public void createEvent(CreateEventDto createEventDto){
        EventType eventype = new EventTypeConverter().convertToEntityAttribute(createEventDto.getEventType());
        eventRepository.save(Event.builder()
                .eventHead(createEventDto.getEventHead())
                .linkedUrl(createEventDto.getLinkedUrl())
                .regDate(createEventDto.getRegDate())
                .eventStart(createEventDto.getEventStart())
                .eventEnd(createEventDto.getEventEnd())
                .eventThumbnail(createEventDto.getEventThumbnail())
                .eventBenefit(createEventDto.getEventBenefit())
                .eventResultDate(createEventDto.getEventResultDate())
                .eventDetailImage(createEventDto.getEventDetailImage())
                .build());
    }

    /*
   //이벤트 주체사
   @Override
   public void createEventPartner(EventPartnerGetDto eventPartnerGetDto){
       eventPartnerRepository.save(EventPartner.builder()
               .eventPartnerName(eventPartnerGetDto.getEventPartnerName())
               .build());
   }


    */
    //이벤트게시글 보기(일반)
    @Override
    public EventGetDto getEvent(Long eventNo){
        Event event=eventRepository.findById(eventNo).get();
        log.info("{}",event);
        ModelMapper mapper=new ModelMapper();
        EventGetDto eventGetDto = mapper.map(event, EventGetDto.class);
        log.info("{}", eventGetDto);
        return eventGetDto;
    }


/*
    @Override
    public EventGetDto myEvent(EventGetDto eventGetDto) {

    }

 */

    /*
    @Override
    public EventPrizeDto getPrizeEvent(Long id){
        Event event=eventRepository.findById(id).get();
        log.info("{}",event);
        ModelMapper mapper=new ModelMapper();
        EventPrizeDto eventPrizeDto = mapper.map(event, EventPrizeDto.class);
        log.info("{}", eventPrizeDto);
        return eventPrizeDto;
    }

     */

    //이벤트리스트로 보기
    @Override
    public List<EventListGetDto> getAllEvent() {
        List<Event> eventlist=eventRepository.findAll();
        //Event에서 EventGetDto로 넣어주는 처리
        //USer Point에 Sample 찾기
        List<EventListGetDto> eventListGetDtoList = eventlist.stream().map(event->{
                    EventType eventype = new EventTypeConverter().convertToEntityAttribute(event.getEventType().getCode());
                    return EventListGetDto.builder()
                            .eventHead(event.getEventHead())
                            .linkedUrl(event.getLinkedUrl())
                            .reg_date(event.getRegDate())
                            .eventStart(event.getEventStart())
                            .eventEnd(event.getEventEnd())
                            .eventThumbnail(event.getEventThumbnail())
                            .build();
                }
        ).toList();
        log.info("Eventlist is : {}" , eventlist);
        return eventListGetDtoList;
    }

    @Override
    public List<EventListOut> checkIngEvent(Integer orderType, Integer pageNo, Integer size) {
        LocalDateTime currentTime =LocalDateTime.now();
        List<Event> ingEvents;
        if (orderType == 30) {
            // OrderType이 30일 때, event_start 이후이고 event_end 이전인 데이터를 reg_date 기준으로 내림차순으로 가져옵니다.
            ingEvents = eventRepository.findByEventStartAfterAndEventEndBeforeOrderByRegDateDesc(currentTime, currentTime);
        } else if (orderType == 40) {
            // OrderType이 40일 때, event_start 이후이고 event_end 이전인 데이터를 event_end 기준으로 오름차순으로 가져옵니다.
            ingEvents = eventRepository.findByEventStartAfterAndEventEndBeforeOrderByEventEndAsc(currentTime, currentTime);
        }
        return null;
    }

    @Override
    public List<EventListOut> endEvent(Integer pageNo, Integer size) {
        LocalDateTime now = LocalDateTime.now();
        List<Event> endEvents = eventRepository.findByEventEndAfter(now);
        return null;
    }

    @Override
    public List<EventListOut> myEventList(Integer pageNo, Integer size) {
        return null;
    }


}
