package dev.raydelcarmen.Technical_Test_Api.shared.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApiConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Social Network API")
                        .description("Social Network API which allows users to post, follow, and view posts.")
                        .version("1.0.0")
                );
    }
}