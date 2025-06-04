package de.steinuntersteinen.jerpapi.model.jobconfirmation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public record JobConfirmationPdf(
        @Id UUID id,
        String filename,
        @JsonIgnore  byte[] file
) {}
