package koo.BasicSpringBatchApplication.job;

import koo.BasicSpringBatchApplication.BatchTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBatchTest
@SpringBootTest
@ExtendWith(SpringExtension.class) // 스프링 환경에서 테스트 할 수 있도록 설정
@ActiveProfiles("test")
@ContextConfiguration(classes = {HelloJobConfig.class, BatchTestConfig.class})
public class HelloJobConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils; // 문제 없음(빨간줄은 인텔리제이 인식 에러)

    @Test
    public void success() throws Exception {
        JobExecution execution = jobLauncherTestUtils.launchJob();

        Assertions.assertEquals(execution.getExitStatus(), ExitStatus.COMPLETED);
    }

}
