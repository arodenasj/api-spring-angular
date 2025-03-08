package es.insinno.config;

import es.insinno.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(UserService userService) {
        return args -> {
            // Create admin user
            userService.createUser(
                    "admin",
                    "admin123",
                    Set.of("ADMIN")
            );

            // Create regular user
            userService.createUser(
                    "user",
                    "user123",
                    Set.of("USER")
            );
        };
    }
}