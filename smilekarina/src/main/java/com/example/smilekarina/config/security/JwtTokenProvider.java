//package com.example.smilekarina.config.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.RequiredArgsConstructor;
//import lombok.Value;
//import lombok.extern.slf4j.Slf4j;
//import org.hibernate.cfg.Environment;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.Map;
//import java.util.function.Function;
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class JwtTokenProvider {
//    @Value("${jwt.secret_key}") String SECRET_KEY;
//    // 토큰 생성 로직
//    private final Environment env;
//    private final String SECRET_KEY = env.getProperty("JWT.SECRET_KEY");
//    public <T> T extractClaim(
//            String token,
//            Function<Claims, T> claimsResolver
//    ){
//        Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    // 토큰 푸는 로직
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseClaimsJwt(token)
//                .getBody();
//    }
//
//    private Key getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String createToken(
//            Map<String, Object> extractClaims,
//        UserDetails userDetails
//
//    ) {
//        return Jwts.builder()
//                .setClaims(extractClaims) // jwt를 이용해 추출한 내용
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
//                .setExpiration(new java.util.Date(System.currentTimeMillis() + env.getProperty("JWT.EXPIRATION_TIME", Long.class)))
//                .signWith(getSigningKey(), SignatureAlgorithm.ES256);
//    }
//
//    public String getUUID(String token) {
//        return extractClaim(token, Claims::getSubject); // 토큰만 가져오기
//    }
//
//    public boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new java.util.Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String UUID = getUUID(token);
//        return (UUID.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//}
