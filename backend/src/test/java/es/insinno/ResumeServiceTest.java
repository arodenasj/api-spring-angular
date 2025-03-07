package es.insinno;

        import es.insinno.entity.Resume;
        import es.insinno.exception.ResourceNotFoundException;
        import es.insinno.repository.ResumeRepository;
        import es.insinno.services.ResumeServiceImpl;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;

        import java.util.Arrays;
        import java.util.List;
        import java.util.Optional;

        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

        class ResumeServiceTest {

            @Mock
            private ResumeRepository resumeRepository;

            @InjectMocks
            private ResumeServiceImpl resumeService;

            @BeforeEach
            void setUp() {
                MockitoAnnotations.openMocks(this);
            }

            @Test
            void testGetAllResumes() {
                Resume resume1 = new Resume(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Developer");
                Resume resume2 = new Resume(2L, "Jane Doe", "jane@example.com", "0987654321", "456 Elm St", "Designer");
                when(resumeRepository.findAll()).thenReturn(Arrays.asList(resume1, resume2));

                List<Resume> resumes = resumeService.getAllResumes();

                assertEquals(2, resumes.size());
                verify(resumeRepository, times(1)).findAll();
            }

            @Test
            void testGetResumeById() {
                Resume resume = new Resume(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Developer");
                when(resumeRepository.findById(1L)).thenReturn(Optional.of(resume));

                Optional<Resume> foundResume = resumeService.getResumeById(1L);

                assertTrue(foundResume.isPresent());
                assertEquals("John Doe", foundResume.get().getName());
                verify(resumeRepository, times(1)).findById(1L);
            }

            @Test
            void testSaveResume() {
                Resume resume = new Resume(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Developer");
                when(resumeRepository.save(resume)).thenReturn(resume);

                Resume savedResume = resumeService.saveResume(resume);

                assertNotNull(savedResume);
                assertEquals("John Doe", savedResume.getName());
                verify(resumeRepository, times(1)).save(resume);
            }

            @Test
            void testDeleteResume() {
                Long id = 1L;
                when(resumeRepository.existsById(id)).thenReturn(true);
                doNothing().when(resumeRepository).deleteById(id);

                resumeService.deleteResume(id);

                verify(resumeRepository, times(1)).existsById(id);
                verify(resumeRepository, times(1)).deleteById(id);
            }

            @Test
            void testDeleteResumeNotFound() {
                Long id = 1L;
                when(resumeRepository.existsById(id)).thenReturn(false);

                assertThrows(ResourceNotFoundException.class, () -> {
                    resumeService.deleteResume(id);
                });

                verify(resumeRepository, times(1)).existsById(id);
                verify(resumeRepository, never()).deleteById(id);
            }
        }