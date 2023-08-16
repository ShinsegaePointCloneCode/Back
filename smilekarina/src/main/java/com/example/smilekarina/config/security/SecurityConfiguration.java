//package com.example.smilekarina.config.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfiguration {
//    private final JwtTokenProvider jwtTokenProvider;
//    private final AuthenticationProvider authendicationProvider;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(CsrfConfigurer::disable)
//                .authorizeHttpRequests(
//                        authorizeHttpRequests -> authorizeHttpRequests
//                                .requestMatchers("/api/v1/auth/**")
//                                .permitAll()
//                                .anyRequest()
//                                .authenticated()
//                )
//                .sessionManagement(
//                        sessionManagement -> sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .authenticationProvider(authendicationProvider)
//                .addFilterBefore((Filter) jwtTokenProvider, UsernamePasswordAuthenticationFilter.class);
//
////        http
////           .csrf().disable()
////           .authorizeRequests()
////           .antMatchers("/api/v1/auth/**").permitAll()
////           .anyRequest().authenticated()
////           .and()
////           .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////           .and()
////           .authenticationProvider(authendicationProvider)
////           .addFilterBefore((Filter) jwtTokenProvider, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//}
