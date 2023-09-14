//package com.example.smilekarina.config.batch;

//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//@Slf4j
//@Configuration
//@EnableBatchProcessing(dataSourceRef = "batchDataSource", transactionManagerRef = "batchTransactionManager")
//@RequiredArgsConstructor
//public class BatchConfig extends DefaultBatchConfiguration {
//    // todo: 일단 스케줄러를 넣고 batch 를 만든다.
//    @Bean
//    public Job job(JobRepository jobRepository) {
//        return new JobBuilder("myJob", jobRepository)
//                //define job flow as needed
//                .build();
//    }
//
//}
