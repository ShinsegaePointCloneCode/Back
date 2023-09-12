package com.example.smilekarina.user.application;

import com.example.smilekarina.config.security.JwtTokenProvider;
import com.example.smilekarina.global.exception.ErrorStateCode;
import com.example.smilekarina.global.exception.TokenInvalidException;
import com.example.smilekarina.user.domain.QUser;
import com.example.smilekarina.user.domain.Roll;
import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.dto.LogInDto;
import com.example.smilekarina.user.dto.UserGetDto;
import com.example.smilekarina.user.dto.UserSignUpDto;
import com.example.smilekarina.user.exception.*;
import com.example.smilekarina.user.vo.*;
import com.example.smilekarina.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    // 유저 추가 로직
    @Override
    @Transactional(readOnly = false)
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
    @Override
    public UserGetDto getUserByLoginId(String loginId) {
        Optional<User> user = userRepository.findByLoginId(loginId);
        user.ifPresent(u -> log.info("user is : {}", u));

        return user.map(u -> modelMapper.map(u, UserGetDto.class))
                .orElse(null);
    }
    // uuid로 dto 만들기
    @Override
    public UserGetDto getUserByUUID(String UUID) {
        Optional<User> user = userRepository.findByUUID(UUID);
        user.ifPresent(u -> log.info("user is : {}", u));
        return user.map(u -> modelMapper.map(user, UserGetDto.class))
                .orElseThrow(() -> new NoUserException(UserErrorStateCode.NOUSER));
    }

    @Override
    public List<UserGetDto> getAllUsers() {
        return null;
    }

    @Override
    @Transactional(readOnly = false)
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
                .orElseThrow(() -> new NoUserException(UserErrorStateCode.NOUSER));
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
    }
    @Override
    public Long getUserId(String loginId) {
        Optional<User> optionalUser =  userRepository.findByLoginId(loginId);
        return optionalUser.orElseThrow(() -> new NoUserException(UserErrorStateCode.NOUSER)).getId();
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
        return optionalUser.orElseThrow(() -> new NoUserException(UserErrorStateCode.NOUSER)).getId();
    }

    @Override
    public FindIDOut findID(String userName, String phone) {
        User user = userRepository.findTopByPhoneAndUserName(phone, userName)
                .orElseThrow(() -> new NoUserException(UserErrorStateCode.NOUSER));
        return FindIDOut.builder()
                .loginId(user.getLoginId())
                .address(user.getAddress())
                .build();
    }

    @Override
    @Transactional(readOnly = false)
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
    @Transactional(readOnly = false)
    public void searchPassword(String loginId, String newPwd) {
        changeUserPassword(loginId, newPwd);
    }

    @Override
    @Transactional(readOnly = false)
    public void withdrawal(String token) {
        String loginId = jwtTokenProvider.getLoginId(token.substring(7));
        User user = userRepository.findByLoginId(loginId)
                        .orElseThrow(() -> new NoUserException(UserErrorStateCode.NOUSER));
        user.setStatus(3);
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

    @Override
    public CheckUserOut getOtherUserInfo(CheckUserIn checkUserIn) {
        User user = userRepository.findTopByPhoneAndUserName(checkUserIn.getPhone(),checkUserIn.getUserName())
                .orElseThrow(()-> new NoUserException(UserErrorStateCode.NOUSER));
        return CheckUserOut.builder()
                .userLoginId(user.getLoginId())
                .userName(user.getName())
                .UUID(user.getUUID())
                .build();
    }

    @Override
    public User getUserFromToken(String token) {
        String loginId = jwtTokenProvider.getLoginId(token.substring(7));
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new TokenInvalidException(ErrorStateCode.TOKEN_INVALID));
    }

    @Override
    // 로그인 했을 경우 등록되어 있다면 로그인 시켜주기
    public LogInDto findOauth(OauthIn oauthIn) {
        if(Objects.equals(oauthIn.getProvider(), "kakao")) {
            User user = userRepository.findByKakaoId(oauthIn.getId())
                    .orElseThrow(() -> new NoAuthException(UserErrorStateCode.NOAUTH));
            String JwtToken = jwtTokenProvider.generateToken(user);
            return LogInDto.builder()
                    .userName(user.getName())
                    .token(JwtToken)
                    .UUID(user.getUUID())
                    .build();
        } else if(Objects.equals(oauthIn.getProvider(), "naver")) {
            User user = userRepository.findByNaverId(oauthIn.getId())
                    .orElseThrow(() -> new NoAuthException(UserErrorStateCode.NOAUTH));
            String JwtToken = jwtTokenProvider.generateToken(user);
            return LogInDto.builder()
                    .userName(user.getName())
                    .token(JwtToken)
                    .UUID(user.getUUID())
                    .build();
        } else throw new NoUserException(UserErrorStateCode.NOUSER);
    }

    @Override
    @Transactional(readOnly = false)
    public void createOauth(String token, OauthIn oauthIn) {
        String loginId = jwtTokenProvider.getLoginId(token.substring(7));
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new NoUserException(UserErrorStateCode.NOUSER));
        if (oauthIn.getProvider().equals("kakao")) {
            user.setKakaoId(oauthIn.getId());
            userRepository.save(user);
        } else if (oauthIn.getProvider().equals("naver")) {
            user.setNaverId(oauthIn.getId());
            userRepository.save(user);
        } else {
            throw new NoUserException(UserErrorStateCode.NOUSER);
        }
    }

    @Transactional(readOnly = false)
    public void changeUserPassword(String loginId, String newPwd) {
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
