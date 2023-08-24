package com.example.smilekarina.event.presentation;
import com.example.smilekarina.event.application.EventService;
import com.example.smilekarina.event.dto.EventPartnerGetDto;
import com.example.smilekarina.global.vo.ResponseOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class EventController {
    private final EventService eventService;
    /*
    @GetMapping("/event/ingevent")
    public ResponseEntity<?>getIngEvent(@RequestParam(value="OrderType") Integer orderType,
                                        @RequestParam(value="pageNo") Integer pageNo,
                                        @RequestParam(value = "size")Integer size){
        ResponseOut<?> responseOut = ResponseOut.success();
        return
    }

     */
   /*
    @PostMapping("/test/insert")
    public void test1(@RequestBody EventPartnerGetDto eventPartnerGetDto) {
        eventService.createEventPartner(eventPartnerGetDto);
    }

    */
}
