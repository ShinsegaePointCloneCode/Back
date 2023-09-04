package com.example.smilekarina.agree.application;

import com.example.smilekarina.agree.dto.AgreeAdvertiseDto;
import com.example.smilekarina.agree.dto.AgreeServiceManageDto;
import com.example.smilekarina.agree.vo.AgreeAdvertiseOut;
import com.example.smilekarina.agree.vo.AgreeServiceManageOut;

import java.util.List;


public interface AgreeService {
    void createAgreeAdvertise(String token, AgreeAdvertiseDto agreeAdvertiseDto);
    AgreeAdvertiseOut getAgreeAdvertise(String token);
    void createAgreeServiceManage(String token, AgreeServiceManageDto agreeServiceManageDto);
    List<AgreeServiceManageOut> getAgreeServiceManage(String token);
}
