package es.insinno.services;

import es.insinno.entity.Resume;

import java.util.List;
import java.util.Optional;

public interface ResumeService {
    List<Resume> getAllResumes();

    Optional<Resume> getResumeById(Long id);

    Resume saveResume(Resume resume);

    void deleteResume(Long id);
}
