package com.springbatch.springbatchexample.config.simple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BaseBatchJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private static final String JOB_NAME = "baseJob";
    private static final String STEP_1_NAME = "baseStep1";
    private static final String STEP_2_NAME = "baseStep1";
    private static final String MESSAGE = "baseJob batch";
    private static final String MESSAGE_2 = "baseJob batch 2";

    @Bean
    public Job baseJob() {
        return this.jobBuilderFactory.get(JOB_NAME).start(helloStep()).next(helloStep2()).build();
    }

    @Bean
    public Step helloStep() {
        return this.stepBuilderFactory.get(STEP_1_NAME).tasklet((contribution, chunkContext) -> {
            log.info("this is baseStep 1, {} \n", MESSAGE);
            return RepeatStatus.FINISHED;
        }).build();

    }

    @Bean
    public Step helloStep2() {
        return this.stepBuilderFactory.get(STEP_2_NAME).tasklet((contribution, chunkContext) -> {
            log.info("this is baseStep 1, {} \n", MESSAGE_2);
            return RepeatStatus.FINISHED;
        }).build();

    }

}
