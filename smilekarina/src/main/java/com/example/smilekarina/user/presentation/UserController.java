package com.example.smilekarina.user.presentation;

import com.example.smilekarina.global.vo.ResponseOut;
import com.example.smilekarina.user.application.UserService;
import com.example.smilekarina.user.dto.LogInDto;
import com.example.smilekarina.user.dto.UserGetDto;
import com.example.smilekarina.user.dto.UserSignUpDto;
import com.example.smilekarina.user.infrastructure.UserRepository;
import com.example.smilekarina.user.vo.*;
//import com.example.smilekarina.utils.redis.application.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;


@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {
    private final ModelMapper modelMapper;
    private final UserService userService;

//    private final RedisService redisService;


    @Operation(summary = "회원 추가 요청", description = "회원을 등록합니다.", tags = { "User Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = UserGetOut.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/user/join/cert")
    public ResponseEntity<?> createUser(@RequestBody UserSignUpIn userSignUpIn) {
        log.info("INPUT Object Data is : {}" , userSignUpIn);
        userService.createUser(modelMapper.map(userSignUpIn, UserSignUpDto.class));
        return ResponseEntity.ok(ResponseOut.success());
    }

    @Operation(summary= "회원 정보 가져오기", description= "token으로 회원정보를 가져온다.", tags = { "User Controller" })
    @GetMapping("/user")
    public ResponseEntity<ResponseOut<?>> getUserByUUID(@RequestHeader("Authorization") String token) {
        UserGetDto userGetDto = userService.getUserDtoFromToken(token);
        log.info("OUTPUT userGetDto is : {}" , userGetDto);
        ResponseOut<?> responseOut = ResponseOut.success(modelMapper.map(userGetDto, UserGetOut.class));
        return ResponseEntity.ok(responseOut);
    }
    @Operation(summary= "회원 정보 수정하기", description= "uuid와 수정된 정보로 회원 정보를 수정한다.", tags = { "User Controller" })
    @PutMapping("/myinfo/modify")
    public ResponseEntity<?> modifyUser(@RequestHeader("Authorization") String token, @RequestBody UserModifyIn userModifyIn) {
        userService.modify(token, userModifyIn);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

    @Operation(summary= "아이디 찾기", description= "login id를 가져와서 중복되는 것인지 확인한다.", tags = { "User Controller" })
    @GetMapping("/join")
    public ResponseEntity<?> checkUser(@RequestParam("loginId") String loginId) {
        UserGetDto userGetDto = userService.getUserByLoginId(loginId);

        if (userGetDto == null) {
            ResponseOut<?> responseOut = ResponseOut.success();
            return ResponseEntity.ok(responseOut);
        }
        ResponseOut<?> responseOut = ResponseOut.checkLogId(loginId);
        return ResponseEntity.ok(responseOut);
    }
    @Operation(summary= "로그인 하기", description= "login id와 password로 로그인 하기", tags = { "User Controller" })
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginIn userLoginIn) {

//        log.info("Login request for user : {}", userLoginIn.getLoginId());
        LogInDto logInDto = userService.loginUser(userLoginIn);
//        log.info(logInDto.getToken());
        String token = logInDto.getToken();
        LogInOut logInOut = modelMapper.map(logInDto, LogInOut.class);

        if (token != null && !token.isEmpty()) {
//            redisService.saveTokenToRedis(userLoginIn.getLoginId(), token);
            // 토큰을 응답 헤더에 담아 보냅니다.
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            return new ResponseEntity<>(ResponseOut.success(logInOut), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseOut.fail());
        }
    }
    @Operation(summary= "아이디 찾기", description= "userName과 폰번호로 id 찾기", tags = { "User Controller" })
    @GetMapping("/member/findIdPw")
    public ResponseEntity<?> authenticateUser(@RequestParam("userName") String userName,
                                              @RequestParam("phone") String phone ) {
        String loginId = userService.findID(userName,phone);
        if (loginId== null) {
            // 아이디를 찾을 수 없을 때
            ResponseOut<?> responseOut = ResponseOut.fail();
            return ResponseEntity.ok(responseOut);
        }
        ResponseOut<?> responseOut = ResponseOut.success(loginId);
        return ResponseEntity.ok(responseOut);
    }
    @Operation(summary= "비밀 번호 바꾸기", description= "인증을 했을 경우 비밀번호를 바꾸는 로직", tags = { "User Controller" })
    @PutMapping("/myinfo/changePwd")
    public ResponseEntity<?> changePassword(@RequestHeader("Authorization") String token, @RequestBody ChangePasswordNewIn changePasswordNewIn) {
        Long state = userService.changePassword(token, changePasswordNewIn.getOldPwd(), changePasswordNewIn.getNewPwd());
        // state 0 : 비밀번호 변경, 1 : 비밀번호가 이전 비밀번호와 동일, 2: 올바른 비밀번호가 아닌 경우
        return switch (state.intValue()) {
            case 0 -> ResponseEntity.ok(ResponseOut.success("비밀번호가 변경되었습니다."));
            case 1 -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseOut.fail("비밀번호가 이전 비밀번호와 동일합니다."));
            case 2 -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseOut.fail("올바른 비밀번호가 아닙니다."));
            default ->
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseOut.fail("알 수 없는 오류가 발생했습니다."));
        };
    }
    @Operation(summary= "비밀 번호 찾기", description= "인증을 안 했을 경우 비밀번호를 바꾸는 로직", tags = { "User Controller" })
    @PutMapping("/member/findPw")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordIn changePasswordIn) {
        Long state = userService.searchPassword(changePasswordIn.getLoginId(), changePasswordIn.getPassword());
        // state 0 : 비밀번호 변경, 1 : 비밀번호가 이전 비밀번호와 동일, 2: 올바른 비밀번호가 아닌 경우
        return switch (state.intValue()) {
            case 0 -> ResponseEntity.ok(ResponseOut.success("비밀번호가 변경되었습니다."));
            case 1 -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseOut.fail("비밀번호가 이전 비밀번호와 동일합니다."));
            default ->
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseOut.fail("알 수 없는 오류가 발생했습니다."));
        };
    }
}
