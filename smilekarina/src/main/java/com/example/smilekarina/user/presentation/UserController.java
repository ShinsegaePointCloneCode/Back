package com.example.smilekarina.user.presentation;

import com.example.smilekarina.global.vo.ResponseOut;
import com.example.smilekarina.user.application.UserService;
import com.example.smilekarina.user.dto.UserGetDto;
import com.example.smilekarina.user.dto.UserSignUpDto;
import com.example.smilekarina.user.vo.UserModifyIn;
import com.example.smilekarina.user.vo.UserGetOut;
import com.example.smilekarina.user.vo.UserSignUpIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
//    @Operation(summary= "회원 가입", discription= "회원 가입 정보를 받아서 가입시킨다.")
    @PostMapping("/user/join/cert")
    public ResponseEntity<?> createUser(@RequestBody UserSignUpIn userSignUpIn) {
        log.info("INPUT Object Data is : {}" , userSignUpIn);
        UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                .loginId(userSignUpIn.getLoginId())
                .password(userSignUpIn.getPassword())
                .name(userSignUpIn.getName())
                .email(userSignUpIn.getEmail())
                .phone(userSignUpIn.getPhone())
                .address(userSignUpIn.getAddress())
                .build();
        userService.createUser(userSignUpDto);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

//    @Operation(summary= "회원 정보 가져오기", discription= "uuid로 회원정보를 가져온다.")
    @GetMapping("/user/{UUID}")
    public ResponseEntity<ResponseOut<?>> getUserByUUID(@PathVariable String UUID) {
        log.info("INPUT UUID is : {}" , UUID);
        UserGetDto userGetDto = userService.getUserByUUID(UUID);
        log.info("OUTPUT userGetDto is : {}" , userGetDto);
        UserGetOut userGetOut = UserGetOut.builder()
                .loginId(userGetDto.getLoginId())
                .userName(userGetDto.getUserName())
                .email(userGetDto.getEmail())
                .phone(userGetDto.getPhone())
                .address(userGetDto.getAddress())
                .build();
        log.info("OUTPUT userGetOut is : {}" , userGetOut);
        ResponseOut<?> responseOut = ResponseOut.success(userGetOut);
        return ResponseEntity.ok(responseOut);
    }
//    @Operation(summary= "회원 정보 수정하기", discription= "uuid와 수정된 정보로 회원 정보를 수정한다.")
    @PutMapping("/myinfo/modify/{UUID}")
    public ResponseEntity<?> modifyUser(@PathVariable String UUID, @RequestBody UserModifyIn userModifyIn) {
        log.info("INPUT UUID is : {}" , UUID);
        userService.modify(UUID, userModifyIn);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

//    @Operation(summary= "아이디 찾기", discription= "login id를 가져와서 중복되는 것인지 확인한다.")
    @GetMapping("/join/{loginId}")
    public ResponseEntity<?> checkUser(@PathVariable String loginId) {
        UserGetDto userGetDto = userService.getUserByLoginId(loginId);

        if (userGetDto == null) {
            ResponseOut<?> responseOut = ResponseOut.success();
            return ResponseEntity.ok(responseOut);
        }
        ResponseOut<?> responseOut = ResponseOut.checkLogId(loginId);
        return ResponseEntity.ok(responseOut);
    }
}
