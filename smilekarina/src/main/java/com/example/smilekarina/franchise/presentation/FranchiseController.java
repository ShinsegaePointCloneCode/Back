package com.example.smilekarina.franchise.presentation;


import com.example.smilekarina.franchise.application.FranchiseService;
import com.example.smilekarina.franchise.dto.FranchiseDto;
import com.example.smilekarina.franchise.dto.MyStoreDto;
import com.example.smilekarina.franchise.vo.*;
import com.example.smilekarina.global.vo.ResponseOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class FranchiseController {
    private final FranchiseService franchiseService;
    private final ModelMapper modelMapper;
    @GetMapping("/mylounge/findStore/region")
    public ResponseEntity<?>getFindStoreRegion(@RequestHeader("Authorization") String token,
                                               @RequestBody FranchiseIn franchiseIn,
                                               Pageable pageable){
        FranchiseDto franchiseDto= modelMapper.map(franchiseIn,FranchiseDto.class);
        Page<RegionOut> regionOutList = franchiseService.findStore(franchiseDto,pageable);
        ResponseOut<?> responseOut = ResponseOut.success(regionOutList);
        return ResponseEntity.ok(responseOut);
    }

    @GetMapping("/mylounge/findStore/map")
    public ResponseEntity<?>getFindStoreList(@RequestHeader("Authorization") String token){
        List<FranchiseOut> franchiseOutList = franchiseService.findStoreList();
        ResponseOut<?> responseOut = ResponseOut.success(franchiseOutList);
        return ResponseEntity.ok(responseOut);
    }
    @PostMapping("/mylounge/regularstore")
    public ResponseEntity<?> myStore(@RequestHeader("Authorization") String token,
                                     @RequestBody MyStoreIn myStoreIn){
        franchiseService.createMyStore(token, modelMapper.map(myStoreIn, MyStoreDto.class));
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }
    @GetMapping("/mylounge/regularstore")
    public ResponseEntity<?>myReStoreList(@RequestHeader("Authorization") String token,
                                       Pageable pageable){
        Page<MyStoreOut> myStoreOuts = franchiseService.myStoreOuts(token,pageable);
        ResponseOut<?> responseOut = ResponseOut.success(myStoreOuts);
        return ResponseEntity.ok(responseOut);
    }

}
