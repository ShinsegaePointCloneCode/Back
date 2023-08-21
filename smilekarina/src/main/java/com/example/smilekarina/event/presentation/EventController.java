package com.example.smilekarina.event.presentation;
import com.example.smilekarina.event.application.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class EventController {
    private final EventService eventService;
    /*
    @PostMapping("/event")
    void createEvent(@RequestBody EventIn eventIn){

    }

     */

}
