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
import org.springframework.security.core.parameters.P;
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
    // 유저 추가 로직
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
    @Transactional
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
        });

        // User 객체가 존재하지 않을 경우 예외 발생
        if (optionalUser.isEmpty()) {
            throw new NoSuchElementException("User with loginId " + loginId + " not found");
        }
    }

    public String loginUser(UserLoginIn userLoginIn) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginIn.getLoginId());
        Integer state = userRepository.findByLoginId(userLoginIn.getLoginId())
                .map(User::getStatus) // 여기서 바로 boolean 값 가져오기
                .orElse(1);       // 기본값은 false 혹은 필요한 값을 넣으세요
        if (state == 1) {
            // password 확인
            if(new BCryptPasswordEncoder().matches(userLoginIn.getPassword(), userDetails.getPassword())) {
                // JWT 토큰 생성 및 반환
                return jwtTokenProvider.generateToken(userDetails);
            } else {
                return null;
            }
        }
        return null;
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
    public String findID(String userName, String phone) {
        return userRepository
                .findByPhoneAndUserName(phone, userName)
                .map(User::getLoginId)
                .orElse(null);
    }

    @Override
    public Long changePassword(String token, String oldPwd, String newPwd) {
        String loginId = jwtTokenProvider.getLoginId(token.substring(7));
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);

        if(new BCryptPasswordEncoder().matches(oldPwd, userDetails.getPassword())) {
            // 비밀번호가 일치하는 경우
            log.info("비밀번호 일치해");
            return changeUserPassword(loginId, newPwd);
        } else {
            return 2L;
        }
    }

    @Override
    public Long searchPassword(String loginId, String newPwd) {
        return changeUserPassword(loginId, newPwd);
    }

    private Long changeUserPassword(String loginId, String newPwd) {
        if (loginId == null || newPwd == null) {
            throw new IllegalArgumentException("Login ID or new password cannot be null");
        }

        User user = userRepository.findByLoginId(loginId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found with the provided login ID");
        }

        String oldPwd = user.getPassword();
        if (oldPwd == null) {
            throw new IllegalArgumentException("Stored password for user is null");
        }

        // 입력된 비밀번호가 이전 비밀번호와 일치하는 경우
        if ((user.getPrePassword() == null) || !new BCryptPasswordEncoder().matches(user.getPrePassword(), newPwd)) {
            user.setPassword(new BCryptPasswordEncoder().encode(newPwd));
            user.setPrePassword(oldPwd);
            userRepository.save(user);
            return 0L;
        }
        return 1L;
    }

}
