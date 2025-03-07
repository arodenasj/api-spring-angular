package es.insinno.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}