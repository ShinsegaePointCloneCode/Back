package com.example.smilekarina.check.presentation;

import com.example.smilekarina.check.application.CheckService;
import com.example.smilekarina.check.dto.CheckDateDto;
import com.example.smilekarina.check.vo.CreateCheckIn;
import com.example.smilekarina.check.vo.CreateRouletteIn;
import com.example.smilekarina.check.vo.RouletteCheckOut;
import com.example.smilekarina.global.vo.ResponseOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class CheckController {
    private final CheckService checkService;
    @PutMapping ("/benefits/pntPlus/attend")
    public ResponseEntity<?> createCheck(@RequestHeader("Authorization") String token, @RequestBody CreateCheckIn createCheckIn) {
        checkService.createCheck(token,createCheckIn.getTime());
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
    @GetMapping("/benefits/pntPlus/attend")
    public ResponseEntity<?> getCheck(@RequestHeader("Authorization") String token) {
        List<Integer> checkList = checkService.getCheck(token);
        ResponseOut<?> responseOut = ResponseOut.success(checkList);
        return ResponseEntity.ok(responseOut);
    }
    @PostMapping("/benefits/pntPlus/roulette")
    public ResponseEntity<?> createRoulette(@RequestHeader("Authorization") String token, @RequestBody CreateRouletteIn createRouletteIn) {
        checkService.createRoulette(token,createRouletteIn.getPoint());
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
    @GetMapping("/benefits/pntPlus/roulette")
    public ResponseEntity<?> getRoulette(@RequestHeader("Authorization") String token) {
        RouletteCheckOut roulettecheckOut = checkService.getRoulette(token);
        ResponseOut<?> responseOut = ResponseOut.success(roulettecheckOut);
        return ResponseEntity.ok(responseOut);
    }

}
