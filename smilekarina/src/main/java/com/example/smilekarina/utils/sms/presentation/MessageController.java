//package com.example.smilekarina.utils.sms.presentation;
//
//import com.example.smilekarina.utils.sms.application.MessageService;
//import com.example.smilekarina.utils.sms.dto.MessageDto;
//import com.example.smilekarina.utils.sms.vo.MessageOut;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestClientException;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URISyntaxException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//@RestController
//@RequestMapping("/api/v1")
//@Slf4j
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RequiredArgsConstructor
//public class MessageController {
//    private final MessageService messageService;
//
//    @GetMapping("/send")
//    public String getSmsPage() {
//        return "sendSms";
//    }
//
//    @PostMapping("/sms/send")
//    public String sendSms(MessageDto messageDto, Model model) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
//        MessageOut response = messageService.sendSms(messageDto);
//        model.addAttribute("response", response);
//        return "result";
//    }
//
//}
