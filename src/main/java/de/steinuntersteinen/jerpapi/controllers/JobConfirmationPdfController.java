package de.steinuntersteinen.jerpapi.controllers;

import de.steinuntersteinen.jerpapi.model.jobconfirmation.JobConfirmation;
import de.steinuntersteinen.jerpapi.model.jobconfirmation.JobConfirmationPdf;
import de.steinuntersteinen.jerpapi.services.JobConfirmationPdfStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(value = "http://localhost:5173", exposedHeaders = "Location")
@RequestMapping("/api/jobconfirmations")
public class JobConfirmationPdfController {

    private final JobConfirmationPdfStorageService storageService;

    private JobConfirmationPdfController(@Autowired JobConfirmationPdfStorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("/upload")
    private ResponseEntity<JobConfirmation> uploadJobConfirmationPdf(
            @RequestParam("file")MultipartFile file, UriComponentsBuilder ucb) throws URISyntaxException, IOException {
        if (
                file == null
                || !"application/pdf".equals(file.getContentType())
                || file.isEmpty()
                || !file.getOriginalFilename().endsWith(".pdf")
        ) {
            return ResponseEntity.badRequest().build();
        }

        UUID id = storageService.store(file);
        URI locationOfStoredJobConfirmationPdf = ucb
                .path("/api/jobconfirmations/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(locationOfStoredJobConfirmationPdf).build();
    }

    @GetMapping("/{id}")
    private ResponseEntity<JobConfirmationPdf> findJobConfirmationPdfMetadataById(@PathVariable("id") String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        Optional<JobConfirmationPdf> jobConfirmationPdfOptional = storageService.get(uuid);
        return jobConfirmationPdfOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

