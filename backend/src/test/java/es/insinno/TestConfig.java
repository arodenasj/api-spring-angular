package es.insinno;
import es.insinno.services.ResumeService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestConfig {
    @Bean
    @Primary
    public ResumeService resumeService() {
        return Mockito.mock(ResumeService.class);
    }
}