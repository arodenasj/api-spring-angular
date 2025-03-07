package es.insinno.controller;

    import es.insinno.entity.Resume;
    import es.insinno.services.ResumeService;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.Parameter;
    import io.swagger.v3.oas.annotations.media.ArraySchema;
    import io.swagger.v3.oas.annotations.media.Content;
    import io.swagger.v3.oas.annotations.media.ExampleObject;
    import io.swagger.v3.oas.annotations.media.Schema;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.responses.ApiResponses;
    import io.swagger.v3.oas.annotations.tags.Tag;
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
        @Operation(summary = "Get all resumes", description = "Retrieves a list of all resumes in the system")
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all resumes",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Resume.class)),
                examples = @ExampleObject(
                    value = "[{\"id\": 1, \"name\": \"Antonio Ródenas\", \"email\": \"anrodenasj@gmail.com\", \"phone\": \"1234567890\", \"address\": \"Calle Falsa 1\", \"position\": \"Developer\"}]"
                )
            )
        )
        public List<Resume> getAllResumes() {
            return resumeService.getAllResumes();
        }

        @GetMapping("/{id}")
        @Operation(summary = "Get resume by ID", description = "Retrieves a specific resume by its ID")
        @ApiResponses(value = {
            @ApiResponse(
                responseCode = "200",
                description = "Resume found",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Resume.class),
                    examples = @ExampleObject(
                        value = "{\"id\": 1, \"name\": \"Antonio Ródenas\", \"email\": \"anrodenasj@gmail.com\", \"phone\": \"1234567890\", \"address\": \"Calle Falsa 1\", \"position\": \"Developer\"}"
                    )
                )
            ),
            @ApiResponse(responseCode = "404", description = "Resume not found")
        })
        public ResponseEntity<Resume> getResumeById(
                @Parameter(description = "ID of the resume to retrieve", required = true, example = "1")
                @PathVariable Long id) {
            return resumeService.getResumeById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        @Operation(summary = "Create resume", description = "Creates a new resume")
        @ApiResponse(
            responseCode = "200",
            description = "Resume created successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Resume.class),
                examples = @ExampleObject(
                    value = "{\"id\": 1, \"name\": \"Antonio Ródenas\", \"email\": \"anrodenasj@gmail.com\", \"phone\": \"1234567890\", \"address\": \"Calle Falsa 1\", \"position\": \"Developer\"}"
                )
            )
        )
        public Resume createResume(
                @Parameter(description = "Resume object to create", required = true)
                @RequestBody Resume resume) {
            return resumeService.saveResume(resume);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Update resume", description = "Updates an existing resume")
        @ApiResponses(value = {
            @ApiResponse(
                responseCode = "200",
                description = "Resume updated successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Resume.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Resume not found")
        })
        public Resume updateResume(
                @Parameter(description = "ID of the resume to update", required = true, example = "1")
                @PathVariable Long id,
                @Parameter(description = "Updated resume object", required = true)
                @RequestBody Resume resume) {
            resume.setId(id);
            return resumeService.saveResume(resume);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Delete resume", description = "Deletes an existing resume")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resume deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Resume not found")
        })
        public void deleteResume(
                @Parameter(description = "ID of the resume to delete", required = true, example = "1")
                @PathVariable Long id) {
            resumeService.deleteResume(id);
        }
    }