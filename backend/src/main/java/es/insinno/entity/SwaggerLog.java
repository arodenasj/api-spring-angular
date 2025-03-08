package es.insinno.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "swagger_logs")
@Data
public class SwaggerLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String method;
    private String uri;
    private String remoteAddress;
    private int responseStatus;
    private LocalDateTime timestamp;
}