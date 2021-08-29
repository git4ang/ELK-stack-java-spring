package ang.neggaw.elks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackages = "ang.neggaw.elks.repositories")
@SpringBootApplication
public class ElkStack_JavaSpringApp {
    public static void main(String[] args) {
        SpringApplication.run(ElkStack_JavaSpringApp.class, args);
    }
}
