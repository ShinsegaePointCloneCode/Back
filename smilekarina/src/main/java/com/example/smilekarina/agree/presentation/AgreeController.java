package com.example.smilekarina.agree.presentation;

import com.example.smilekarina.agree.application.AgreeService;
import com.example.smilekarina.agree.dto.AgreeAdvertiseDto;
import com.example.smilekarina.agree.vo.AgreeAdvertiseIn;
import com.example.smilekarina.agree.vo.AgreeAdvertiseOut;
import com.example.smilekarina.global.vo.ResponseOut;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class AgreeController {
    private final AgreeService agreeService;
    private final ModelMapper modelMapper;

    @Operation(summary= "광고정보 수신관리 만들기"
            , description= "광고정보 수신관리 정보를 만듭니다.6개의 값을 넣으면 그 값이 저장됩니다"
            , tags = { "Event Controller" })
    @PutMapping("/myinfo/infoRcvAgree")
    public ResponseEntity<?> createAgreeAdvertise(@RequestHeader("Authorization") String token, @RequestBody AgreeAdvertiseIn agreeAdvertiseIn) {
        AgreeAdvertiseDto agreeAdvertiseDto = modelMapper.map(agreeAdvertiseIn,AgreeAdvertiseDto.class);
        agreeService.createAgreeAdvertise(token,agreeAdvertiseDto);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

    @Operation(summary= "광고정보 수신관리 검색"
            , description= "광고정보 수신관리 정보를 가져옵니다. 6개의 값을 가져옵니다."
            , tags = { "Event Controller" })
    @GetMapping("/myinfo/infoRcvAgree")
    public ResponseEntity<?> getAgreeAdvertise(@RequestHeader("Authorization") String token) {
        AgreeAdvertiseOut agreeAdvertiseOut = agreeService.getAgreeAdvertise(token);
        ResponseOut<?> responseOut = ResponseOut.success(agreeAdvertiseOut);
        return ResponseEntity.ok(responseOut);
    }

//    @Operation(summary= "서비스 동의 관리 만들기"
//            , description= "서비스 동의 관리를 만듭니다. 있으면 수정하고 없으면 만듭니다."
//            , tags = { "Event Controller" })
//    @PostMapping("/myinfo/serciveAgree")
//    public ResponseEntity<?> createAgreeServiceManage(@RequestHeader("Authorization") String token) {
//        AgreeAdvertiseOut agreeAdvertiseOut = agreeService.getAgreeAdvertise(token);
//        ResponseOut<?> responseOut = ResponseOut.success();
//        return ResponseEntity.ok(responseOut);
//    }

}
