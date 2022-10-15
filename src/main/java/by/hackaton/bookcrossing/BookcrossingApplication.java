package by.hackaton.bookcrossing;

import by.hackaton.bookcrossing.dto.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableScheduling
@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class BookcrossingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookcrossingApplication.class, args);
    }

}
