package com.example.smilekarina.user.application;

import com.example.smilekarina.config.security.JwtTokenProvider;
import com.example.smilekarina.user.domain.Roll;
import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.dto.UserGetDto;
import com.example.smilekarina.user.dto.UserSignUpDto;
import com.example.smilekarina.user.vo.UserLoginIn;
import com.example.smilekarina.user.vo.UserModifyIn;
import com.example.smilekarina.user.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper;
    @Override
    public void createUser(UserSignUpDto userSignUpDto) {
        log.info("craeteUser : {}",userSignUpDto);
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
        log.info("savedUser : {}",user.getLoginId());
        userRepository.save(user);
    }

    public UserGetDto getUserByLoginId(String loginId) {
        Optional<User> user = userRepository.findByLoginId(loginId);
        user.ifPresent(u -> log.info("user is : {}", u));

        return user.map(u -> modelMapper.map(u, UserGetDto.class))
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public UserGetDto getUserByUUID(String UUID) {
        Optional<User> user = userRepository.findByUUID(UUID);
        user.ifPresent(u -> log.info("user is : {}", u));
        return user.map(u -> modelMapper.map(user, UserGetDto.class))
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }


    @Override
    public List<UserGetDto> getAllUsers() {
        return null;
    }

    @Override
    @Transactional
    public void modify(String UUID, UserModifyIn userModifyIn) {
        Optional<User> optionalUser = userRepository.findByUUID(UUID);

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
        });

        // User 객체가 존재하지 않을 경우 예외 발생
        if (optionalUser.isEmpty()) {
            throw new NoSuchElementException("User with UUID " + UUID + " not found");
        }
    }

    public String loginUser(UserLoginIn userLoginIn) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginIn.getLoginId());
        // password 확인
        if(new BCryptPasswordEncoder().matches(userLoginIn.getPassword(), userDetails.getPassword())) {
            // JWT 토큰 생성 및 반환
            return jwtTokenProvider.generateToken(userDetails);
        } else {
            return null;
        }
    }

    @Override
    public Long getUserId(String loginId) {
        Optional<User> optionalUser =  userRepository.findByLoginId(loginId);
        return optionalUser.orElseThrow(() -> new NoSuchElementException("User not found")).getId();
    }

    public Long getUserIdFromToken(String token) {
        String loginId = jwtTokenProvider.getLoginId(token.substring(7));
        log.info("loginid는 들고온닥 {}", loginId);
        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
        return optionalUser.orElseThrow(() -> new NoSuchElementException("User not found")).getId();
    }

}
