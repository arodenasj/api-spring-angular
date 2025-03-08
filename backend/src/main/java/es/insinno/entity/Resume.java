package es.insinno.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resume")
@Getter
@Setter
@Schema(description = "Resume entity representing a person's professional information")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier", example = "1")
    private Long id;

    @NotBlank(message = "Name is required")
    @Schema(description = "Full name", example = "Antonio Ródenas")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(description = "Email address", example = "anrodenasj@gmail.com")
    private String email;

    @Schema(description = "Phone number", example = "1234567890")
    private String phone;

    @Schema(description = "Physical address", example = "Calle Falsa 1")
    private String address;

    @Schema(description = "Current or desired position", example = "Developer")
    private String position;

    public Resume() {
    }

    public Resume(Long id, String name, String email, String phone, String address, String position) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.position = position;
    }
}