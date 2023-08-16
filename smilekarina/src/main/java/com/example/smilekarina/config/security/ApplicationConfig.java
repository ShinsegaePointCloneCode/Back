//package com.example.smilekarina.config.security;
//
//import com.example.smilekarina.user.infrastructure.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public record ApplicationConfig(UserRepository userRepository) {
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return loginId -> userRepository.findByLoginId(loginId)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found : {}" + loginId));
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    private PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}