package pro.codeschool.camelmicroservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.codeschool.camelmicroservices.transformer.UserTransformer;

@Configuration
public class AppConfig {

    @Bean
    UserTransformer userTransformer() {
        return new UserTransformer();
    }

}
