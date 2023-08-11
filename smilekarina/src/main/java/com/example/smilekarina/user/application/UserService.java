package com.example.smilekarina.user.application;

import com.example.smilekarina.user.dto.UserGetDto;
import com.example.smilekarina.user.dto.UserSignUpDto;

import java.util.List;

public interface UserService {
    void createUser(UserSignUpDto userSignUpDto);
    UserGetDto getUserByLoginId(String loginId);
    UserGetDto getUserByUUID(String UUID);
    List<UserGetDto> getAllUsers();
}
