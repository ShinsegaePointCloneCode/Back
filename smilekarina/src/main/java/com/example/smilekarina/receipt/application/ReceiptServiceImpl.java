package com.example.smilekarina.receipt.application;

import com.example.smilekarina.point.application.PointService;
import com.example.smilekarina.point.dto.PointAddDto;
import com.example.smilekarina.receipt.domain.Receipt;
import com.example.smilekarina.receipt.domain.SmartReceipt;
import com.example.smilekarina.receipt.infrastructure.ReceiptRepository;
import com.example.smilekarina.receipt.infrastructure.SmartReceiptRepository;
import com.example.smilekarina.receipt.vo.SmartReceiptPointIn;
import com.example.smilekarina.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptServiceImpl implements ReceiptService{

    private final UserService userService;
    private final PointService pointService;
    private final ReceiptRepository receiptRepository;
    private final SmartReceiptRepository smartReceiptRepository;

    // 영수증 인증 완료 후 적립
    @Override
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
}
