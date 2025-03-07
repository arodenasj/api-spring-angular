package es.insinno.controller;

import es.insinno.Resume;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@CrossOrigin("*")
public class ResumeController {

    @GetMapping
    public List<Resume> getResume() {
        return List.of(
                new Resume(1L, "Antonio Ródenas", "anrodenasj@gmail.com", "123456789", "Calle Falsa 123", "Java Developer")
        );
    }

    @GetMapping("/{id}")
    public Resume getResumeById(@PathVariable Long id) {
        return new Resume(1L, "Antonio Ródenas", "anrodenasj@gmail.com", "123456789", "Calle Falsa 123", "Java Developer");
    }

}
