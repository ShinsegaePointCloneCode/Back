package com.example.smilekarina.user.application;

import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.dto.LogInDto;
import com.example.smilekarina.user.dto.UserGetDto;
import com.example.smilekarina.user.dto.UserSignUpDto;
import com.example.smilekarina.user.vo.UserLoginIn;
import com.example.smilekarina.user.vo.UserModifyIn;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createUser(UserSignUpDto userSignUpDto);
    UserGetDto getUserByLoginId(String loginId);
    UserGetDto getUserByUUID(String UUID);
    List<UserGetDto> getAllUsers();
    void modify(String UUID, UserModifyIn userModifyIn);
    LogInDto loginUser(UserLoginIn userLoginIn);
    UserGetDto getUserDtoFromToken(String token);
    Long getUserId(String loginId);
    Long getUserIdFromToken(String token);
    String findID(String userName,String phone);
    void changePassword(String token, String oldPwd, String newPwd);
    void searchPassword(String loginId,String newPwd);
}
