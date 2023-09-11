package com.example.smilekarina.event.presentation;
import com.example.smilekarina.event.application.EventService;
import com.example.smilekarina.event.dto.EventListGetDto;
import com.example.smilekarina.event.vo.EventListOut;
import com.example.smilekarina.global.vo.ResponseOut;
import com.example.smilekarina.event.vo.EventOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class EventController {
    private final EventService eventService;
    private final ModelMapper modelMapper;
    @GetMapping("/event/ingevent/{orderType}")
    public ResponseEntity<?> getIngEvent(@PathVariable(value = "orderType") Integer orderType,
                                         Pageable pageable){
        Page<EventListOut> eventIngListOut = eventService.checkIngEvent(orderType, pageable);
        ResponseOut<?> responseOut = ResponseOut.success(eventIngListOut);
        return ResponseEntity.ok(responseOut);
    }

    @GetMapping("/event/endevent")
    public ResponseEntity<?>getEndEvent(Pageable pageable){
        Page<EventListOut> eventEndListOut =eventService.endEvent(pageable);
        ResponseOut<?> responseOut = ResponseOut.success(eventEndListOut);
        return ResponseEntity.ok(responseOut);
    }

    @GetMapping("/event/winevents")
    public ResponseEntity<?>getPrizeEndListEvent(Pageable pageable){
        Page<EventListOut> eventPrizeListOut =eventService.prizeEvent(pageable);
        ResponseOut<?> responseOut = ResponseOut.success(eventPrizeListOut);
        return ResponseEntity.ok(responseOut);
    }
/*
    @PostMapping("/benefits/myEvent")
    public ResponseEntity<?> myEvent(@RequestBody EventGetDto eventGetDto) {
        eventService.myEvent(eventGetDto);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
 */
    //query string 파라미터로 id 값에 해당하는 데이터 요청 -> 이야기해보기 string이 아니고 Long 으로 받으면 안되나용 ..?ㅎㅎㅎ

    @GetMapping("/event")
    public ResponseEntity<?>detailEvent(@RequestParam(value = "eventNo")Long eventNo){
        EventOut eventOut =eventService.getEvent(eventNo);
        ResponseOut<?> responseOut = ResponseOut.success(modelMapper.map(eventOut, EventOut.class));
        return ResponseEntity.ok(responseOut);
    }



    @GetMapping("/benefits/myEvent")
    public ResponseEntity<?>detailMyEvent(@RequestParam(value = "page")Integer pageNo,
                                        @RequestParam(value = "size") Integer size){
        List<EventListOut> myEventListOut=eventService.myEventList(pageNo,size);
        ResponseOut<?> responseOut =ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
    @GetMapping("/event/1")
    public ResponseEntity<?>event1(){
        List<EventListGetDto> eventListGetDtoList =eventService.getAllEvent();
        ResponseOut<?> responseOut =ResponseOut.success(eventListGetDtoList);
        return ResponseEntity.ok(responseOut);
    }
}
