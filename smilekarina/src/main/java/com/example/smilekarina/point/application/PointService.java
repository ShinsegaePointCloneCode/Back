package com.example.smilekarina.point.application;

import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.point.dto.PointPasswordCheckDto;
import com.example.smilekarina.point.vo.PointInfoOut;

import java.time.LocalDate;

public interface PointService {

    // 사용가능포인트 조회
    Integer getUsablePoint(Long userId);

    // 포인트 내역 상단 조회
    PointInfoOut getPointInfo(Long userId);

    // 포인트 데이터 등록하기
    Long registerPoint(PointAddDto pointAddDto);

    // 포인트 비밀번호 일치 확인
    Boolean checkPointPassword(PointPasswordCheckDto pointPasswordCheckDto);

    // 포인트 비밀번호 수정
    void modifyPointPassword(String token, String pointPassword);

    void amountMonthPoint(Long userId, LocalDate targetDate);
}
