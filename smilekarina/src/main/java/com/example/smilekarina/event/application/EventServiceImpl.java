package com.example.smilekarina.event.application;

import com.example.smilekarina.event.domain.*;
import com.example.smilekarina.event.dto.*;
import com.example.smilekarina.event.infrastructure.EventDetailImageRepository;
import com.example.smilekarina.event.infrastructure.EventRepository;
import com.example.smilekarina.event.infrastructure.MyEventListRepository;
import com.example.smilekarina.event.vo.EventListOut;
import com.example.smilekarina.event.vo.EventOut;
import com.example.smilekarina.user.application.UserService;
import com.example.smilekarina.user.domain.User;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;


import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService{
    private final UserService userService;
    private final EventRepository eventRepository;
    private final EventDetailImageRepository eventDetailImageRepository;
    private final JPAQueryFactory query;
    private final MyEventListRepository myEventListRepository;
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
    public Page<EventListOut> checkIngEvent(Integer orderType, Pageable pageable) {
        LocalDateTime currentTime =LocalDateTime.now();
        QEvent event = QEvent.event;
        OrderSpecifier<?> orderSpecifier = switch (orderType) {
            case 30 -> event.createdDate.desc();
            case 40 -> event.eventEnd.asc();
            default -> event.createdDate.desc();  // 기본 정렬
        };
        Expression<String> eventTypeAsString = Expressions.stringTemplate(
                "CAST({0} AS string)",
                event.eventType
        );
        BooleanExpression baseCondition = event.eventStart.loe(currentTime).and(event.eventEnd.goe(currentTime));
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
                .where(baseCondition)
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
    public Page<EventListOut> prizeEvent(Pageable pageable) {
        LocalDateTime now =LocalDateTime.now();
        QEvent event = QEvent.event;
        Expression<String> eventTypeAsString = Expressions.stringTemplate(
                "CAST({0} AS string)",
                event.eventType
        );
        BooleanExpression baseCondition = event.eventEnd.lt(now);
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
                .where(baseCondition,
                        event.eventType.eq(EventType.PARTICIPATE))
                .orderBy(event.eventEnd.desc())
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
    @Transactional(readOnly = false)
    public void createMyEvent(String token, EventListGetDto dto) {
        User user = userService.getUserFromToken(token);
        MyEventList myEventList=MyEventList.builder()
                .prizeBool(false)
                .eventId(dto.getEventId())
                .user(user)
                .build();
        myEventListRepository.save(myEventList);
    }

    @Override
    public Page<EventListOut> myPAEventList(String token, Pageable pageable) {
        User user = userService.getUserFromToken(token);
        QEvent event = QEvent.event;
        QMyEventList myEventList =QMyEventList.myEventList;
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
                .leftJoin(myEventList).on(myEventList.eventId.eq(event.id).and(myEventList.user.eq(user)))
                .where(
                        myEventList.user.eq(user),
                        myEventList.prizeBool.eq(false))
                .orderBy(event.eventEnd.desc())
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
    public Page<EventListOut> myWinEvent(String token, Pageable pageable) {
        User user = userService.getUserFromToken(token);
        QEvent event = QEvent.event;
        QMyEventList myEventList =QMyEventList.myEventList;
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
                .leftJoin(myEventList).on(myEventList.eventId.eq(event.id).and(myEventList.user.eq(user)))
                .where(
                        myEventList.user.eq(user),
                        myEventList.prizeBool.eq(true))
                .orderBy(event.eventEnd.desc())
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
    public Page<EventListOut> endEvent(Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        QEvent event = QEvent.event;
        Expression<String> eventTypeAsString = Expressions.stringTemplate(
                "CAST({0} AS string)",
                event.eventType
        );
        BooleanExpression baseCondition = event.eventEnd.lt(now);
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
                .where(baseCondition)
                .orderBy(event.eventEnd.desc())
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

}
