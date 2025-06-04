package de.steinuntersteinen.jerpapi.services;

import de.steinuntersteinen.jerpapi.model.jobconfirmation.JobConfirmationPdf;
import de.steinuntersteinen.jerpapi.repositories.JobConfirmationPdfRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobConfirmationPdfStorageService {

    private final JobConfirmationPdfRepository repo;

    private JobConfirmationPdfStorageService(JobConfirmationPdfRepository repo) {
        this.repo = repo;
    }

    public UUID store(MultipartFile file) throws IOException {
        return repo.save(new JobConfirmationPdf(
                null,
                file.getOriginalFilename(),
                file.getBytes()))
                .id();
    }

    public Optional<JobConfirmationPdf> get(UUID uuid) {
        return repo.findById(uuid);
    }

}
