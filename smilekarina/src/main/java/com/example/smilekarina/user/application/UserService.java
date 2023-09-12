package com.example.smilekarina.user.application;

import com.example.smilekarina.user.domain.User;
import com.example.smilekarina.user.dto.LogInDto;
import com.example.smilekarina.user.dto.UserGetDto;
import com.example.smilekarina.user.dto.UserSignUpDto;
import com.example.smilekarina.user.vo.*;

import java.util.List;

public interface UserService {
    Long createUser(UserSignUpDto userSignUpDto);
    UserGetDto getUserByLoginId(String loginId);
    UserGetDto getUserByUUID(String UUID);
    List<UserGetDto> getAllUsers();
    void modify(String UUID, UserModifyIn userModifyIn);
    LogInDto loginUser(UserLoginIn userLoginIn);
    UserGetDto getUserDtoFromToken(String token);
    Long getUserId(String loginId);
    Long getUserIdFromToken(String token);
    FindIDOut findID(String userName, String phone);
    void changePassword(String token, String oldPwd, String newPwd);
    void searchPassword(String loginId,String newPwd);
    void withdrawal(String token);
    void authenticatePassword(String token, AuthenticatePasswordIn authenticatePasswordIn);
    CheckUserOut getOtherUserInfo(CheckUserIn checkUserIn);
    User getUserFromToken(String token);
    LogInDto findOauth(OauthIn oauthIn);
    void createOauth(String token, OauthIn oauthIn);
    void checkPointPassword(String token, PointPasswordIn passwordIn);
}
