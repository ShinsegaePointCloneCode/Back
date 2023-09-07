package com.example.smilekarina.franchise.presentation;


import com.example.smilekarina.franchise.application.FranchiseService;
import com.example.smilekarina.franchise.domain.Franchise;
import com.example.smilekarina.franchise.dto.FranchiseDto;
import com.example.smilekarina.franchise.vo.FranchiseIn;
import com.example.smilekarina.franchise.vo.FranchiseOut;
import com.example.smilekarina.global.vo.ResponseOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class FranchiseController {
    private final FranchiseService franchiseService;
    private final ModelMapper modelMapper;
    @GetMapping("/mylounge/findstore/1 ")
    public ResponseEntity<?>getFindStore(){
        List<FranchiseOut> franchiseOutList = franchiseService.findStore();
        ResponseOut<?> responseOut = ResponseOut.success(franchiseOutList);
        return ResponseEntity.ok(responseOut);
    }

    @GetMapping("/mylounge/findstore")
    public ResponseEntity<?>getFindStoreList(@RequestHeader("Authorization") String token,
                                             @RequestBody FranchiseIn franchiseIn,
                                             Pageable pageable){
        FranchiseDto franchiseDto= modelMapper.map(franchiseIn,FranchiseDto.class);
        Page<FranchiseOut> franchiseOutList = franchiseService.findstorelist(franchiseDto,pageable);
        ResponseOut<?> responseOut = ResponseOut.success(franchiseOutList);
        return ResponseEntity.ok(responseOut);
    }
}
