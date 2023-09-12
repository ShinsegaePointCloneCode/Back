package com.example.smilekarina.receipt.application;

import com.example.smilekarina.point.application.PointService;
import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.point.vo.PointContentOut;
import com.example.smilekarina.receipt.domain.Receipt;
import com.example.smilekarina.receipt.domain.ReceiptAccept;
import com.example.smilekarina.receipt.domain.SmartReceipt;
import com.example.smilekarina.receipt.infrastructure.ReceiptAcceptRepository;
import com.example.smilekarina.receipt.infrastructure.ReceiptRepository;
import com.example.smilekarina.receipt.infrastructure.SmartReceiptRepository;
import com.example.smilekarina.receipt.vo.SmartReceiptPointIn;
import com.example.smilekarina.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReceiptServiceImpl implements ReceiptService{

    private final UserService userService;
    private final PointService pointService;
    private final ReceiptRepository receiptRepository;
    private final SmartReceiptRepository smartReceiptRepository;
    private final ReceiptAcceptRepository receiptAcceptRepository;

    // 영수증 인증 완료 후 적립
    @Override
    @Transactional(readOnly = false)
    public void registerSmartReceipt(String token, SmartReceiptPointIn smartReceiptPointIn) {

        // 토큰 정보에서 userId 값 가져 오기
        Long userId = userService.getUserIdFromToken(token);

        // 포인트테이블에 새 데이터 추가하기
        PointAddDto pointAddDto = PointAddDto.builder()
                .point(smartReceiptPointIn.getPoint())
                .pointType(smartReceiptPointIn.getPointType())
                .used(false)
                .userId(userId)
                .build();
        Long pointId = pointService.registerPoint(pointAddDto);

        // 영수증 테이블에 새 데이터 추가하기
        Receipt receipt = Receipt.builder()
                .receiptNumber(smartReceiptPointIn.getReceiptNumber())
                .build();
        Receipt resultReceipt = receiptRepository.save(receipt);
        Long receiptId = resultReceipt.getId();

        // 중간테이블에 새 데이터 추가하기
        SmartReceipt smartReceipt = SmartReceipt.builder()
                .pointId(pointId)
                .userId(userId)
                .receiptId(receiptId)
                .franchiseId(smartReceiptPointIn.getFranchiseId())
                .branchName(smartReceiptPointIn.getBranchName())
                .build();
        smartReceiptRepository.save(smartReceipt);
    }

    // 스마트 영수증 조회가 가능한 곳 적립/사용 포인트 조회
    @Override
    public PointContentOut getSmartReceipt(Long pointId) {

        SmartReceipt smartReceipt = smartReceiptRepository.findByPointId(pointId);

        Receipt receipt = receiptRepository.findById(smartReceipt.getId()).orElseThrow(IllegalArgumentException::new);;

        return PointContentOut.builder()
                .franchiseName(smartReceipt.getBranchName())
                .receiptNumber(receipt.getReceiptNumber())
                .build();
    }

    // 일반 적립/사용 포인트 조회
    @Override
    public PointContentOut getReceiptAccept(Long pointId) {

        ReceiptAccept receiptAccept = receiptAcceptRepository.findByPointId(pointId);

        return PointContentOut.builder()
                .franchiseName(receiptAccept.getBranchName())
                .build();
    }
}
