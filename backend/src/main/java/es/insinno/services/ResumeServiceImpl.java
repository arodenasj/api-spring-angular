package es.insinno.services;

import es.insinno.entity.Resume;
import es.insinno.exception.ResourceNotFoundException;
import es.insinno.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Autowired
    public ResumeServiceImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Override
    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    @Override
    public Optional<Resume> getResumeById(Long id) {
        return resumeRepository.findById(id);
    }

    @Override
    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    @Override
    public void deleteResume(Long id) {
        if (!resumeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resume not found with id " + id);
        }
        resumeRepository.deleteById(id);
    }
}