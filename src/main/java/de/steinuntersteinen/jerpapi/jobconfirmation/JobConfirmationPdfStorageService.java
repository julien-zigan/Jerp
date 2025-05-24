package de.steinuntersteinen.jerpapi.jobconfirmation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class JobConfirmationPdfStorageService {

    private final JobConfirmationPdfRepository repo;

    private JobConfirmationPdfStorageService(JobConfirmationPdfRepository repo) {
        this.repo = repo;
    }

    public JobConfirmationPdf store(MultipartFile file) throws IOException {
        return repo.save(new JobConfirmationPdf(null, file.getOriginalFilename(), file.getBytes()));
    }

}
