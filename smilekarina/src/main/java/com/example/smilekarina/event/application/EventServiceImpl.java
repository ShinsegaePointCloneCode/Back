package com.example.smilekarina.event.application;

import com.example.smilekarina.event.domain.*;
import com.example.smilekarina.event.dto.*;
import com.example.smilekarina.event.infrastructure.EventDetailImageRepository;
import com.example.smilekarina.event.infrastructure.EventRepository;
import com.example.smilekarina.event.vo.EventListOut;
import com.example.smilekarina.event.vo.EventOut;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;


import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@Transactional(readOnly = true)
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;
    private final EventDetailImageRepository eventDetailImageRepository;
    private final JPAQueryFactory query;
    /*
    @Override
    public void createEvent(CreateEventDto createEventDto){
        EventType eventype = new EventTypeConverter().convertToEntityAttribute(createEventDto.getEventType());
        eventRepository.save(Event.builder()
                .eventHead(createEventDto.getEventHead())
                .linkedUrl(createEventDto.getLinkedUrl())
                .eventStart(createEventDto.getEventStart())
                .eventEnd(createEventDto.getEventEnd())
                .eventThumbnail(createEventDto.getEventThumbnail())
                .eventBenefit(createEventDto.getEventBenefit())
                .eventResultDate(createEventDto.getEventResultDate())
                .build());
    }

     */

    //이벤트게시글 보기(일반)

    @Override
    public EventOut getEvent(Long eventNo){
        Event event=eventRepository.findById(eventNo).orElse(null);
        log.info("{}",event);
        if (event != null) {
            List<EventDetailImage> eventDetailImages = eventDetailImageRepository.findByEventId(event.getId());
            List<String> eventDetailImageStrings = eventDetailImages.stream()
                    .map(EventDetailImage::getEventDetailImage) // EventDetailImage에서 문자열 값을 추출하는 메서드 호출
                    .toList();

            return EventOut.builder()
                    .eventId(event.getId())
                    .eventHead(event.getEventHead())
                    .linkedUrl(event.getLinkedUrl())
                    .regDate(event.getCreatedDate())
                    .eventResultDate(event.getEventResultDate())
                    .eventStart(event.getEventStart())
                    .eventEnd(event.getEventEnd())
                    .eventType(event.getEventType().getValue())
                    .eventDetailImage(eventDetailImageStrings)
                    .build();
        } else {
            throw new NoSuchElementException("이벤트가 없습니다.");
        }

    }




    @Override
    public EventGetDto myEvent(EventGetDto eventGetDto) {
    return null;
    }


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
                            .regDate(event.getCreatedDate())
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
    public Page<EventListOut> checkIngEvent(Integer orderType, Pageable pageable) {
        LocalDateTime currentTime =LocalDateTime.now();
        QEvent event = QEvent.event;
        OrderSpecifier<?> orderSpecifier = switch (orderType) {
            case 30 -> event.createdDate.asc();
            case 40 -> event.eventEnd.desc();
            default -> event.createdDate.asc();  // 기본 정렬
        };
        Expression<String> eventTypeAsString = Expressions.stringTemplate(
                "CAST({0} AS string)",
                event.eventType
        );
        List<EventListOut> eventListOutQuery = query
                .select(Projections.constructor(EventListOut.class,
                    event.id,
                    event.eventHead,
                    event.linkedUrl,
                    event.createdDate,
                    event.eventStart,
                    event.eventEnd,
                    event.eventThumbnail,
                    eventTypeAsString
                ))
                .from(event)
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query
                .select(event.count())
                .from(event)
                .fetchOne();
        if (count == null) count = 0L;
        return new PageImpl<>(eventListOutQuery,pageable,count);
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
