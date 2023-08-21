package com.example.smilekarina.user.presentation;

import com.example.smilekarina.global.vo.ResponseOut;
import com.example.smilekarina.user.application.UserService;
import com.example.smilekarina.user.dto.UserGetDto;
import com.example.smilekarina.user.dto.UserSignUpDto;
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

    @Operation(summary= "회원 정보 가져오기", description= "uuid로 회원정보를 가져온다.", tags = { "User Controller" })
    @GetMapping("/user/{UUID}")
    public ResponseEntity<ResponseOut<?>> getUserByUUID(@PathVariable String UUID) {
        log.info("INPUT UUID is : {}" , UUID);
        UserGetDto userGetDto = userService.getUserByUUID(UUID);
        log.info("OUTPUT userGetDto is : {}" , userGetDto);
        ResponseOut<?> responseOut = ResponseOut.success(modelMapper.map(userGetDto, UserGetOut.class));
        return ResponseEntity.ok(responseOut);
    }
    @Operation(summary= "회원 정보 수정하기", description= "uuid와 수정된 정보로 회원 정보를 수정한다.", tags = { "User Controller" })
    @PutMapping("/myinfo/modify/{UUID}")
    public ResponseEntity<?> modifyUser(@PathVariable String UUID, @RequestBody UserModifyIn userModifyIn) {
        log.info("INPUT UUID is : {}" , UUID);
        userService.modify(UUID, userModifyIn);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

    @Operation(summary= "아이디 찾기", description= "login id를 가져와서 중복되는 것인지 확인한다.", tags = { "User Controller" })
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
    @Operation(summary= "로그인 하기", description= "login id와 password로 로그인 하기", tags = { "User Controller" })
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginIn userLoginIn) {

        log.info("Login request for user : {}", userLoginIn.getLoginId());
        String token = userService.loginUser(userLoginIn);

        if (token != null && !token.isEmpty()) {
//            redisService.saveTokenToRedis(userLoginIn.getLoginId(), token);
            // 토큰을 응답 헤더에 담아 보냅니다.
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);

            return new ResponseEntity<>(null, headers, HttpStatus.OK); // body 부분은 null로 설정
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
