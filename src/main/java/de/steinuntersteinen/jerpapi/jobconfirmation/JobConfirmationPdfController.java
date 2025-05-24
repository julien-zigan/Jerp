package de.steinuntersteinen.jerpapi.jobconfirmation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping("/api/jobconfirmations")
@RestController
public class JobConfirmationPdfController {

    private final JobConfirmationPdfStorageService storageService;

    private JobConfirmationPdfController(@Autowired JobConfirmationPdfStorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("/upload")
    private ResponseEntity<JobConfirmation> uploadJobConfirmationPdf(
            @RequestParam("file")MultipartFile file, UriComponentsBuilder ucb) throws URISyntaxException, IOException {
        if (!"application/pdf".equals(file.getContentType()) || file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        JobConfirmationPdf storedJobConfirmationPdf = storageService.store(file);
        URI locationOfStoredJobConfirmationPdf = ucb
                .path("/api/confirmations/{id}")
                .buildAndExpand(storedJobConfirmationPdf.id())
                .toUri();

        return ResponseEntity.created(locationOfStoredJobConfirmationPdf).build();
    }
}

