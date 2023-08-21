//package com.example.smilekarina.utils.redis.application;
//
//import com.example.smilekarina.utils.redis.domain.Token;
//import com.example.smilekarina.utils.redis.infrastructure.TokenRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class RedisServiceImpl implements RedisService{
//    private TokenRepository tokenRepository;
//    public Token saveTokenToRedis(String loginId, String tokenString) {
//        Token token = Token.builder()
//                .loginId(loginId)
//                .accessToken(tokenString)
//                .build();
//        return tokenRepository.save(token);
//    }
//
//    public Optional<Token> getTokenFromRedis(String loginId) {
//        return tokenRepository.findById(loginId);
//    }
//
//    public void deleteTokenFromRedis(String loginId) {
//        tokenRepository.deleteById(loginId);
//    }
//}
