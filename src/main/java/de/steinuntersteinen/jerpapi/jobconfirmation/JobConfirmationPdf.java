package de.steinuntersteinen.jerpapi.jobconfirmation;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public record JobConfirmationPdf(@Id UUID id, String filename, byte[] file) {
}
