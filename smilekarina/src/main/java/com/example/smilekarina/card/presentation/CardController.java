package com.example.smilekarina.card.presentation;

import com.example.smilekarina.card.application.CardService;
import com.example.smilekarina.card.dto.AffiliateCardDto;
import com.example.smilekarina.card.dto.PointCardDto;
import com.example.smilekarina.card.vo.*;
import com.example.smilekarina.global.vo.ResponseOut;
import com.example.smilekarina.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class CardController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CardService cardService;

    /*
        신규 포인트 카드 등록, 임시 발급 카드 인증(외부에서 인증 완료 가정)
     */
    @PostMapping("/pointcard/register")
    public ResponseEntity<?> registerPointCard(@RequestHeader("Authorization") String token, @RequestBody PointCardIn pointCardIn) {

        Long userId = userService.getUserIdFromToken(token);

        PointCardDto pointCardDto = PointCardDto.builder()
                .userId(userId)
                .cardNumber(pointCardIn.getCardNumber())
                .issueType(pointCardIn.getIssueType())
                .issueStore(pointCardIn.getIssueStore())
                .build();

        cardService.registerPointCard(pointCardDto);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

    /*
        제휴 멤버십 카드 등록
     */
    @PostMapping("/affiliatecard/register")
    public ResponseEntity<?> registerAffiliateCard(@RequestHeader("Authorization") String token, @RequestBody AffiliateCardIn affiliateCardIn) {

        Long userId = userService.getUserIdFromToken(token);

        AffiliateCardDto affiliateCardDto = AffiliateCardDto.builder()
                .userId(userId)
                .cardNumber(affiliateCardIn.getCardNumber())
                .issueStore(affiliateCardIn.getIssueStore())
                .lastName(affiliateCardIn.getLastName())
                .build();

        cardService.registerAffiliateCard(affiliateCardDto);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

    /*
        온라인 카드 조회
     */
    @GetMapping("/pointcard/online")
    public ResponseEntity<ResponseOut<?>> getOnlineCard(@RequestHeader("Authorization") String token) {

        Long userId = userService.getUserIdFromToken(token);

        List<OnlinePointCardOut> onlinePointCardDtolist = cardService.getOnlinePointCardList(userId);
        ResponseOut<?> responseOut = ResponseOut.success(onlinePointCardDtolist);
        return ResponseEntity.ok(responseOut);
    }

    /*
        제휴 신용카드 조회
     */
    @GetMapping("/pointcard/credit")
    public ResponseEntity<ResponseOut<?>> getCreditCard(@RequestHeader("Authorization") String token) {

        Long userId = userService.getUserIdFromToken(token);

        List<CreditCardOut> creditCardOutList = cardService.getCreditCardList(userId);
        ResponseOut<?> responseOut = ResponseOut.success(creditCardOutList);
        return ResponseEntity.ok(responseOut);
    }

    /*
        오프라인 카드 조회
     */
    @GetMapping("/pointcard/offline")
    public ResponseEntity<ResponseOut<?>> getOfflineCard(@RequestHeader("Authorization") String token) {

        Long userId = userService.getUserIdFromToken(token);

        List<OfflinePointCardOut> offlinePointCardOutList = cardService.getOfflinePointCardList(userId);
        ResponseOut<?> responseOut = ResponseOut.success(offlinePointCardOutList);
        return ResponseEntity.ok(responseOut);
    }

    /*
        제휴 포인트 카드 조회 (화면구현유무에 따라 작업예정)
     */

    /*
        제휴 마일리지 카드 조회 (화면구현유무에 따라 작업예정)
     */

    /*
        제휴 카드 수정 - API명세서에도 추가필요(화면구현유무에 따라 추가예정)
     */

    /*
        제휴 카드 삭제 - API명세서에도 추가필요(화면구현유무에 따라 추가예정)
     */

    /*
        대표 카드 수정 - API명세서에도 추가필요(화면구현유무에 따라 추가예정)
     */

    /*
        포인트카드 번호 조회(바코드 보기 위함)
     */
    @GetMapping("/card/pointcard")
    public ResponseEntity<ResponseOut<?>> getBarcodePointCard(@RequestHeader("Authorization") String token) {

        Long userId = userService.getUserIdFromToken(token);

        String cardNumber = cardService.getPointCardNumber(userId);

        BarcodePointCardOut barcodePointCardOut = BarcodePointCardOut.builder()
                .cardNumber(cardNumber)
                .build();

        ResponseOut<?> responseOut = ResponseOut.success(barcodePointCardOut);
        return ResponseEntity.ok(responseOut);




    }


}
