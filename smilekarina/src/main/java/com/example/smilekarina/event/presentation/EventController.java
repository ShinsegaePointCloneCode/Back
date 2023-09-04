package com.example.smilekarina.event.presentation;
import com.example.smilekarina.event.application.EventService;
import com.example.smilekarina.event.vo.EventListOut;
import com.example.smilekarina.global.vo.ResponseOut;
import com.example.smilekarina.event.dto.EventGetDto;
import com.example.smilekarina.event.vo.EventOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class EventController {
    private final EventService eventService;
    private final ModelMapper modelMapper;
    @GetMapping("/event/ingevent")
    public ResponseEntity<?>getIngEvent(@RequestParam(value="OrderType") Integer orderType,
                                        @RequestParam(value="pageNo") Integer pageNo,
                                        @RequestParam(value = "size")Integer size){
        List<EventListOut> eventIngListOut = eventService.checkIngEvent(orderType,pageNo,size);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

    @GetMapping("/event/endevent")
    public ResponseEntity<?>getIngEvent(@RequestParam(value="pageNo") Integer pageNo,
                                        @RequestParam(value = "size")Integer size){
        List<EventListOut> eventEndListOut =eventService.endEvent(pageNo,size);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
/*
    @PostMapping("/event/myEvent")
    public ResponseEntity<?> myEvent(@RequestBody EventGetDto eventGetDto) {
        eventService.myEvent(eventGetDto);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
 */
    //query string 파라미터로 id 값에 해당하는 데이터 요청 -> 이야기해보기 string이 아니고 Long 으로 받으면 안되나용 ..?ㅎㅎㅎ
    @GetMapping("/event")
    public ResponseEntity<?>detailEvent(@RequestParam(value = "eventNo")Long eventNo){
        EventGetDto eventGetDto =eventService.getEvent(eventNo);
        ResponseOut<?> responseOut = ResponseOut.success(modelMapper.map(eventGetDto, EventOut.class));
        return ResponseEntity.ok(responseOut);
    }

    @GetMapping("/benefits/myEvent")
    public ResponseEntity<?>detailEvent(@RequestParam(value = "pageNo")Integer pageNo,
                                        @RequestParam(value = "size") Integer size){
        List<EventListOut> myEventListOut=eventService.myEventList(pageNo,size);
        ResponseOut<?> responseOut =ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
}
