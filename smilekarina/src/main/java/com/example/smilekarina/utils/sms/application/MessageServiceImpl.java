//package com.example.smilekarina.utils.sms.application;
//
//import com.example.smilekarina.utils.sms.dto.MessageDto;
//import com.example.smilekarina.utils.sms.vo.MessageIn;
//import com.example.smilekarina.utils.sms.vo.MessageOut;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.codec.binary.Base64;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class MessageServiceImpl implements MessageService{
//    private final Environment env;
//
//    private String serviceId = env.getProperty("SMS.SERVICE_ID");
//    private String accessKey = env.getProperty("SMS.ACCESS_KEY");
//    private String secretKey = env.getProperty("SMS.SECRET_KEY");
//    private String phone = env.getProperty("SMS.PHONE_NUMBER");
//
//    @Override
//    public MessageOut sendSms(MessageDto messageDto) throws JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {
//        Long time = System.currentTimeMillis();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("x-ncp-apigw-timestamp", time.toString());
//        headers.set("x-ncp-iam-access-key", accessKey);
//        headers.set("x-ncp-apigw-signature-v2", makeSignature(time));
//
//        List<MessageDto> messages = new ArrayList<>();
//        messages.add(messageDto);
//
//        MessageIn request = MessageIn.builder()
//                .type("SMS")
//                .contentType("COMM")
//                .countryCode("82")
//                .from(phone)
//                .content(messageDto.getContent())
//                .messages(messages)
//                .build();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String body = objectMapper.writeValueAsString(request);
//        HttpEntity<String> httpBody = new HttpEntity<>(body, headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
//
//        return restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+ serviceId +"/messages"), httpBody, MessageOut.class);
//    }
//    private String makeSignature(Long time) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
//        String space = " ";
//        String newLine = "\n";
//        String method = "POST";
//        String url = "/sms/v2/services/"+ this.serviceId+"/messages";
//        String timestamp = time.toString();
//        String accessKey = this.accessKey;
//        String secretKey = this.secretKey;
//
//        String message = method +
//                space +
//                url +
//                newLine +
//                timestamp +
//                newLine +
//                accessKey;
//
//        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
//        Mac mac = Mac.getInstance("HmacSHA256");
//        mac.init(signingKey);
//
//        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
//
//        return Base64.encodeBase64String(rawHmac);
//    }
//}
