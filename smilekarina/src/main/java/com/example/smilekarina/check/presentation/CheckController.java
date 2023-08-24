package com.example.smilekarina.check.presentation;

import com.example.smilekarina.check.application.CheckService;
import com.example.smilekarina.global.vo.ResponseOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class CheckController {
    private final CheckService checkService;
    @PostMapping("/benefits/pntPlus/attend")
    public ResponseEntity<?> createCheck(@RequestHeader("Authorization") String token, @RequestBody LocalDateTime time) {
        checkService.createCheck(token,time);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

}
