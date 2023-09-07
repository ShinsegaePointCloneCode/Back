package com.example.smilekarina.user.application;

import com.example.smilekarina.config.security.JwtTokenProvider;
import com.example.smilekarina.global.exception.ErrorStateCode;
import com.example.smilekarina.global.exception.TokenInvalidException;
import com.example.smilekarina.user.domain.Roll;
import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.dto.LogInDto;
import com.example.smilekarina.user.dto.UserGetDto;
import com.example.smilekarina.user.dto.UserSignUpDto;
import com.example.smilekarina.user.exception.NoPasswordException;
import com.example.smilekarina.user.exception.SamePasswordException;
import com.example.smilekarina.user.exception.UserErrorStateCode;
import com.example.smilekarina.user.vo.AuthenticatePasswordIn;
import com.example.smilekarina.user.vo.FindIDOut;
import com.example.smilekarina.user.vo.UserLoginIn;
import com.example.smilekarina.user.vo.UserModifyIn;
import com.example.smilekarina.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;


@Service
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    // 유저 추가 로직
    @Override
//    @Transactional(readOnly = false)
    public Long createUser(UserSignUpDto userSignUpDto) {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        String hashedPassword = new BCryptPasswordEncoder().encode(userSignUpDto.getPassword());

        User user = User.builder()
                .loginId(userSignUpDto.getLoginId())
                .UUID(uuidString)
                .userName(userSignUpDto.getUserName())
                .password(hashedPassword)
                .email(userSignUpDto.getEmail())
                .phone(userSignUpDto.getPhone())
                .address(userSignUpDto.getAddress())
                .status(1)
                .roll(Roll.USER)
                .build();
        userRepository.save(user);
        return user.getId();
    }

    public UserGetDto getUserByLoginId(String loginId) {
        Optional<User> user = userRepository.findByLoginId(loginId);
        user.ifPresent(u -> log.info("user is : {}", u));

        return user.map(u -> modelMapper.map(u, UserGetDto.class))
                .orElse(null);
    }
    //uuid로 dto 만들기
    @Override
    public UserGetDto getUserByUUID(String UUID) {
        Optional<User> user = userRepository.findByUUID(UUID);
        user.ifPresent(u -> log.info("user is : {}", u));
        return user.map(u -> modelMapper.map(user, UserGetDto.class))
                .orElse(null);
    }

    @Override
    public List<UserGetDto> getAllUsers() {
        return null;
    }

    @Override
//    @Transactional(readOnly = false)
    public void modify(String token, UserModifyIn userModifyIn) {
        String loginId = jwtTokenProvider.getLoginId(token.substring(7));
        Optional<User> optionalUser = userRepository.findByLoginId(loginId);

        // User 객체가 존재할 경우만 내부 로직 실행
        optionalUser.ifPresent(modifiedUser -> {
            // 주소 변경
            if (null != userModifyIn.getAddress()) {
                modifiedUser.setAddress(userModifyIn.getAddress());
            }
            // 이메일 변경
            if (null != userModifyIn.getEmail()) {
                modifiedUser.setEmail(userModifyIn.getEmail());
            }
            userRepository.save(modifiedUser);
        });

        // User 객체가 존재하지 않을 경우 예외 발생
        if (optionalUser.isEmpty()) {
            throw new NoSuchElementException("User with loginId " + loginId + " not found");
        }
    }
    @Override
    public LogInDto loginUser(UserLoginIn userLoginIn){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    userLoginIn.getLoginId(),
                    userLoginIn.getPassword()
            )
        );
        User user = userRepository.findByLoginId(userLoginIn.getLoginId())
                .orElseThrow(() -> new NoSuchElementException("없는 유저 입니다."));
        if(user.getStatus() == 3) {
            throw new NoSuchElementException("탈퇴한 유저 입니다.");
        } else if (user.getStatus() == 2) {
            throw new NoSuchElementException("휴먼 유저 입니다.");
        }
        String JwtToken = jwtTokenProvider.generateToken(user);
        return LogInDto.builder()
                .userName(user.getName())
                .token(JwtToken)
                .UUID(user.getUUID())
                .build();
//        UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginIn.getLoginId());
//        User user = userRepository.findByLoginId(userLoginIn.getLoginId()).orElse(null);
//        // 유저가 존재하지 않거나 삭제한 유저가 아니면
////        log.info("user : {} ", user);
//        if (user != null && (user.getStatus() == 1)) {
//            // password 확인
//            if(new BCryptPasswordEncoder().matches(userLoginIn.getPassword(), userDetails.getPassword())) {
//                // JWT 토큰 생성 및 반환
//                return LogInDto.builder()
//                        .userName(user.getName())
//                        .token(jwtTokenProvider.generateToken(userDetails))
//                        .UUID(user.getUUID())
//                        .build();
//            }
//        }
//        return null;
    }
    @Override
    public Long getUserId(String loginId) {
        Optional<User> optionalUser =  userRepository.findByLoginId(loginId);
        return optionalUser.orElseThrow(() -> new NoSuchElementException("User not found")).getId();
    }
    @Override
    public UserGetDto getUserDtoFromToken(String token) {
        String loginId = jwtTokenProvider.getLoginId(token.substring(7));
        return getUserByLoginId(loginId);
    }
    @Override
    public Long getUserIdFromToken(String token) {
        String loginId = jwtTokenProvider.getLoginId(token.substring(7));
        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
        return optionalUser.orElseThrow(() -> new NoSuchElementException("User not found")).getId();
    }

    @Override
    public FindIDOut findID(String userName, String phone) {
        User user = userRepository.findByPhoneAndUserName(phone, userName).orElseThrow(() ->
                new NoSuchElementException("해당 유저가 없습니다."));
        return FindIDOut.builder()
                .loginId(user.getLoginId())
                .address(user.getAddress())
                .build();
    }

    @Override
//    @Transactional(readOnly = false)
    public void changePassword(String token, String oldPwd, String newPwd) {
        String loginId = jwtTokenProvider.getLoginId(token.substring(7));
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);

        if(new BCryptPasswordEncoder().matches(oldPwd, userDetails.getPassword())) {
            // 비밀번호가 일치하는 경우
            log.info("비밀번호 일치해");
            changeUserPassword(loginId, newPwd);
        }
    }

    @Override
//    @Transactional(readOnly = false)
    public void searchPassword(String loginId, String newPwd) {
        changeUserPassword(loginId, newPwd);
    }

    @Override
    //    @Transactional(readOnly = false)
    public void withdrawal(String token) {
        String loginId = jwtTokenProvider.getLoginId(token.substring(7));
        User user = userRepository.findByLoginId(loginId).orElse(null);
        Objects.requireNonNull(user).setStatus(3);
        userRepository.save(user);
    }

    @Override
    public void authenticatePassword(String token, AuthenticatePasswordIn authenticatePasswordIn) {
        String loginId = jwtTokenProvider.getLoginId(token.substring(7));
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
        // 비밀번호가 일치하는 경우
        if(new BCryptPasswordEncoder().matches(authenticatePasswordIn.getPassword(), userDetails.getPassword())) {
            return;
        } else {
            throw new NoPasswordException(UserErrorStateCode.NOPASSWORD);
        }
    }
//    @Transactional(readOnly = false)
    private void changeUserPassword(String loginId, String newPwd) {
        if (loginId == null || newPwd == null) {
            throw new IllegalArgumentException("Login ID or new password 가 null입니다.");
        }
        User user = userRepository.findByLoginId(loginId).orElseThrow(
                () ->new TokenInvalidException(ErrorStateCode.TOKEN_INVALID));
        String oldPwd = user.getPassword();

        // 입력된 비밀번호가 이전 비밀번호와 일치하는 경우
        if ((user.getPrePassword() == null) || !new BCryptPasswordEncoder().matches(user.getPrePassword(), newPwd)) {
            user.setPassword(new BCryptPasswordEncoder().encode(newPwd));
            user.setPrePassword(oldPwd);
            userRepository.save(user);
        } else {
            throw new SamePasswordException(UserErrorStateCode.SAMEPREPASSWORD);
        }
    }

}
