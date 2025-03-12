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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
