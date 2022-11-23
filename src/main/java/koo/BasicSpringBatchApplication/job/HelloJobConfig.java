package koo.BasicSpringBatchApplication.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HelloJobConfig { // 위의 망치 오른쪽 박스를 클릭한다음 Edit Configuration 선택, environment의 program arguments에서 --spring.batch.job.names=helloJob 넣고 apply하여 job을 등록해야한다.

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean("helloJob")
    public Job helloJob(Step helloStep) {
        return jobBuilderFactory.get("helloJob") // Job을 생성
                .incrementer(new RunIdIncrementer()) // incrementer => 잡을 실행할때마다 횟수를 일정하게 증가시켜준다
                .start(helloStep) // step을 지정해야한다.
                .build();
    }

    @JobScope // job이 실행되는 동안만 살아있는다.
    @Bean("helloStep")
    public Step helloStep(Tasklet tasklet) {
        return stepBuilderFactory.get("helloStep")
                .tasklet(tasklet)
                .build();
    }

    @StepScope // step이 실행되는 동안만 살아있는다.
    @Bean
    public Tasklet tasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("Hello, world!");
            return RepeatStatus.FINISHED; // Tasklet은 반복상태를 정해주어야한다. => 헬로월드 출력하고 종료 할 것이라서 계속 반복하는 CONTINUABLE이 아닌 FINISHED 선택
        };
    }

//    @Bean
//    public Job sampleJob(JobRepository jobRepository, Step sampleStep) {
//        return this.jobBuilderFactory.get("sampleJob")
//                .repository(jobRepository)
//                .start(sampleStep())
//                .build();
//    }
//
//    @Bean
//    public Step sampleStep(PlatformTransactionManager transactionManager) {
//        return this.stepBuilderFactory.get("sampleStep")
//                .transactionManager(transactionManager)
//                .<String, String>chunk(10)
//                .reader(itemReader())
//                .writer(itemWriter())
//                .build();
//    }

}
