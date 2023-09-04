package com.example.smilekarina.point.application;

import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.point.dto.PointGetDto;
import com.example.smilekarina.point.dto.PointPasswordCheckDto;

import java.util.List;

public interface PointService {

    // 사용가능포인트 조회
    Integer getUsablePoint(Long userId);

    // 포인트 데이터 등록하기
    Long registerPoint(PointAddDto pointAddDto);

    // 포인트 비밀번호 일치 확인
    Boolean checkPointPassword(PointPasswordCheckDto pointPasswordCheckDto);

    // 포인트 비밀번호 수정
    void modifyPointPassword(String token, String pointPassword);




    // 밑에는 강사님코드 (참고용) *******************************
//    void createPoint(PointAddDto pointAddDto);
//    List<PointGetDto> getPointByUser(Long userId);

}
