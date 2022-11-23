package koo.BasicSpringBatchApplication.job;

import koo.BasicSpringBatchApplication.core.domain.PlainText;
import koo.BasicSpringBatchApplication.core.repository.PlainTextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PlainTextJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PlainTextRepository plainTextRepository;

    @Bean("plainTextJob")
    public Job plainTextJob(Step plainTextStep) { // 위의 망치 오른쪽 박스를 클릭한다음 Edit Configuration 선택, environment의 program arguments에서 --spring.batch.job.names=plainTextJob 넣고 apply하여 job을 등록해야한다.
        return jobBuilderFactory.get("plainTextJob")
                .incrementer(new RunIdIncrementer())
                .start(plainTextStep)
                .build();
    }

    @JobScope
    @Bean("plainTextStep")
    public Step plainTextStep(ItemReader plainTextItemReader, ItemProcessor plainTextProcessor, ItemWriter plainTextWriter) {
        return stepBuilderFactory.get("plainTextStep")
                .<PlainText, String>chunk(5) // <읽어올 타입, 프로세싱하고 난 뒤 타입>
                .reader(plainTextItemReader)
                .processor(plainTextProcessor)
                .writer(plainTextWriter)
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<PlainText> plainTextItemReader() {
        return new RepositoryItemReaderBuilder<PlainText>()
                .name("plainTextReader")
                .repository(plainTextRepository)
                .methodName("findBy") // PlainTextRepository의 findBy 함수
                .pageSize(5)
                .arguments(new ArrayList<>()) // PlainTextRepository의 findBy 함수의 pageable은 PlainTextRepository에서 자동으로 넘겨받고, 다른 argument는 입력할 필요가 없으므로 빈 리스트를 넘긴다.
                .sorts(Collections.singletonMap("id", Sort.Direction.DESC))
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<PlainText, String> plainTextProcessor() { // <프로세싱하기 전 타입, 프로세싱하고 난 뒤 타입>
        return item -> "processed " + item.getText(); // 람다식, 원래 아이템명 앞에 processed를 붙이도록 프로세싱
    }

    @StepScope
    @Bean
    public ItemWriter<String> plainTextWriter() {
        return items -> {
//          items.forEach(System.out::println);
            items.forEach(item -> System.out.println(item));
            System.out.println("==== chunk is finished ====");
        };
    }

}
