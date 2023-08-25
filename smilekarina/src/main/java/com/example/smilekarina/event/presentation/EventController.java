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
        List<EventListOut> eventListOutList = eventService.checkIngEvent(orderType,pageNo,size);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }


   /*
    @PostMapping("/test/insert")
    public void test1(@RequestBody EventPartnerGetDto eventPartnerGetDto) {
        eventService.createEventPartner(eventPartnerGetDto);
    }

    */
    @GetMapping("/event")
    public ResponseEntity<?>detailEvent(@RequestParam(value = "eventNo")Integer eventNo){
        EventGetDto eventGetDto =eventService.checkEvent(eventNo);
        ResponseOut<?> responseOut = ResponseOut.success(modelMapper.map(eventGetDto, EventOut.class));
        return ResponseEntity.ok(responseOut);
    }

}
