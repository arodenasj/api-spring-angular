package es.insinno;

import es.insinno.TestConfig;
import es.insinno.controller.ResumeController;
import es.insinno.entity.Resume;
import es.insinno.services.ResumeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResumeController.class)
@Import(TestConfig.class)
class ResumeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResumeService resumeService;

    private Resume resume1;
    private Resume resume2;

    @BeforeEach
    void setUp() {
        resume1 = new Resume(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Developer");
        resume2 = new Resume(2L, "Jane Doe", "jane@example.com", "0987654321", "456 Elm St", "Designer");
        reset(resumeService);
    }


    @Test
    void testGetAllResumes() throws Exception {
        when(resumeService.getAllResumes()).thenReturn(Arrays.asList(resume1, resume2));

        mockMvc.perform(get("/api/resumes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"));

        verify(resumeService, times(1)).getAllResumes();
    }

    @Test
    void testGetResumeById() throws Exception {
        when(resumeService.getResumeById(1L)).thenReturn(Optional.of(resume1));

        mockMvc.perform(get("/api/resumes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(resumeService, times(1)).getResumeById(1L);
    }

    @Test
    void testCreateResume() throws Exception {
        when(resumeService.saveResume(any(Resume.class))).thenReturn(resume1);

        mockMvc.perform(post("/api/resumes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"John Doe\", \"email\": \"john@example.com\", \"phone\": \"1234567890\", \"address\": \"123 Main St\", \"position\": \"Developer\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(resumeService, times(1)).saveResume(any(Resume.class));
    }

    @Test
    void testUpdateResume() throws Exception {
        when(resumeService.saveResume(any(Resume.class))).thenReturn(resume1);

        mockMvc.perform(put("/api/resumes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"John Doe\", \"email\": \"john@example.com\", \"phone\": \"1234567890\", \"address\": \"123 Main St\", \"position\": \"Developer\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(resumeService, times(1)).saveResume(any(Resume.class));
    }

    @Test
    void testDeleteResume() throws Exception {
        doNothing().when(resumeService).deleteResume(1L);

        mockMvc.perform(delete("/api/resumes/1"))
                .andExpect(status().isOk());

        verify(resumeService, times(1)).deleteResume(1L);
    }
}