package es.insinno.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Resume Data Transfer Object")
public class ResumeDTO {
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

    @Schema(description = "Avatar image")
    private String imageUrl;

    // Getters and setters
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
