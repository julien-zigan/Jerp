package de.steinuntersteinen.jerpapi.jobconfirmation;

import org.springframework.data.annotation.Id;

public record JobConfirmationPdf(@Id Long id, String filename, byte[] file) {
}
