package com.example.smilekarina.event.application;

import com.example.smilekarina.event.domain.Event;
import com.example.smilekarina.event.dto.EventGetDto;
import com.example.smilekarina.event.infrastructure.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    @Override
    public void createEvent(EventGetDto eventGetDto){
        eventRepository.save(Event.builder()
                .eventHead(eventGetDto.getEventHead())
                .linkedUrl(eventGetDto.getLinkedUrl())
                .eventStart(eventGetDto.getEventStart())
                .eventEnd(eventGetDto.getEventEnd())
                .eventThumbnail(eventGetDto.getEventThumbnail())
                .eventType(eventGetDto.getEventType())
                .eventDetailImage(eventGetDto.getEventDetailImage())
                .build());
    }
    @Override
    public EventGetDto getEvent(Long id){
        Event event=eventRepository.findById(id).get();
        log.info("{}",event);
        ModelMapper mapper=new ModelMapper();
        EventGetDto eventGetDto = mapper.map(event,EventGetDto.class);
        log.info("{}",eventGetDto);
        return eventGetDto;
    }
}
