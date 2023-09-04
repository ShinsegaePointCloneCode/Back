package com.example.smilekarina.franchise.presentation;


import com.example.smilekarina.franchise.application.FranchiseService;
import com.example.smilekarina.franchise.domain.Franchise;
import com.example.smilekarina.franchise.vo.FranchiseOut;
import com.example.smilekarina.global.vo.ResponseOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class FranchiseController {
    private final FranchiseService franchiseService;
    @GetMapping("/mylounge/findstore")
    public ResponseEntity<?>getFindStore(){
        List<FranchiseOut> franchiseOutList = franchiseService.findStore();
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
}
