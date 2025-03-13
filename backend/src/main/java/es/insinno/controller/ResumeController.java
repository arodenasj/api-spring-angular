package es.insinno.controller;

import es.insinno.DTO.ResumeDTO;
import es.insinno.entity.Resume;
import es.insinno.services.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/resumes")
@CrossOrigin("*")
@Tag(name = "Resume Management", description = "Operations related to resume management")
public class ResumeController {
    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping
    @Operation(summary = "Get all resumes")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = Resume.class))
            )
    )
    public List<Resume> getAllResumes() {
        return resumeService.getAllResumes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get resume by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404")
    })
    public ResponseEntity<Resume> getResumeById(@PathVariable Long id) {
        return resumeService.getResumeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create resume")
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = Resume.class))
    )
    public Resume createResume(@Valid @RequestBody ResumeDTO resumeDTO) {
        Resume resume = new Resume();
        resume.setName(resumeDTO.getName());
        resume.setEmail(resumeDTO.getEmail());
        resume.setPhone(resumeDTO.getPhone());
        resume.setAddress(resumeDTO.getAddress());
        resume.setPosition(resumeDTO.getPosition());
        return resumeService.saveResume(resume);
    }

  @PutMapping("/{id}")
  @Operation(summary = "Update resume")
  @ApiResponses({
      @ApiResponse(responseCode = "200"),
      @ApiResponse(responseCode = "404")
  })
  public ResponseEntity<Resume> updateResume(@PathVariable Long id, @Valid @RequestBody ResumeDTO resumeDTO) {
      return resumeService.getResumeById(id).map(existingResume -> {
          existingResume.setName(resumeDTO.getName());
          existingResume.setEmail(resumeDTO.getEmail());
          existingResume.setPhone(resumeDTO.getPhone());
          existingResume.setAddress(resumeDTO.getAddress());
          existingResume.setPosition(resumeDTO.getPosition());
          existingResume.setImageUrl(resumeDTO.getImageUrl());
          return ResponseEntity.ok(resumeService.saveResume(existingResume));
      }).orElse(ResponseEntity.notFound().build());
  }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete resume")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404")
    })
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        if (resumeService.getResumeById(id).isPresent()) {
            resumeService.deleteResume(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

  @Value("${file.upload-dir}")
  private String uploadDir;

  @PostMapping("/upload")
  @Operation(summary = "Upload profile image")
  public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
      if (file.isEmpty()) {
          return ResponseEntity.badRequest().body("Please select a file");
      }

      try {
          // Create absolute path for uploads directory
          Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
          Files.createDirectories(uploadPath);

          // Generate unique filename
          String originalFileName = file.getOriginalFilename();
          String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
          String fileName = UUID.randomUUID().toString() + fileExtension;

          // Save file
          Path targetLocation = uploadPath.resolve(fileName);
          Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

          // Return relative URL
          String fileUrl = "/uploads/" + fileName;
          return ResponseEntity.ok().body(Map.of("imageUrl", fileUrl));
      } catch (IOException e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Failed to upload file: " + e.getMessage());
      }
  }
}
