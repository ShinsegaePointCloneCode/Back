package com.example.smilekarina;

//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing // base entity 자동 적용
//@EnableScheduling // 스케줄링 활성화
//@EnableBatchProcessing // 배치기능 활성화
@SpringBootApplication
public class SmilekarinaApplication {

    public static void main(String[] args) {
        SpringApplication.run( SmilekarinaApplication.class, args);
    }

}
