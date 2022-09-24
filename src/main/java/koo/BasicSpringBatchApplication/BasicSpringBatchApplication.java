package koo.BasicSpringBatchApplication;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class BasicSpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicSpringBatchApplication.class, args);
	}

}
