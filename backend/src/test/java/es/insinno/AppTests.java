package es.insinno;

        import es.insinno.config.TestSecurityConfig;
        import org.junit.jupiter.api.Test;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.context.annotation.Import;
        import org.springframework.test.context.ActiveProfiles;
        import org.springframework.test.context.TestPropertySource;

        @SpringBootTest(
            webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
            classes = BackendApplication.class
        )
        @Import(TestSecurityConfig.class)
        @TestPropertySource(properties = {
            "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
            "spring.datasource.username=sa",
            "spring.datasource.password=",
            "spring.jpa.hibernate.ddl-auto=create-drop",
            "spring.jpa.show-sql=true",
            "spring.security.user.name=test",
            "spring.security.user.password=test",
            "spring.main.allow-bean-definition-overriding=true",
            "springdoc.api-docs.enabled=false",
            "springdoc.swagger-ui.enabled=false",
            "spring.h2.console.enabled=false"
        })
        @ActiveProfiles("test")
        class AppTests {

            @Test
            void contextLoads() {
            }
        }