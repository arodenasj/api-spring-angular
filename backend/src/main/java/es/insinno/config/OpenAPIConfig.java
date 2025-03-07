package es.insinno.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI resumeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Resume Management API")
                        .description("API for managing resumes in the application")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Antonio Ródenas")
                                .email("anrodenasj@gmail.com")
                                .url("https://github.com/arodenasj"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}