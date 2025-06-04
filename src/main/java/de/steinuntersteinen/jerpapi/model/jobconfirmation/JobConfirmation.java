package de.steinuntersteinen.jerpapi.model.jobconfirmation;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public record JobConfirmation(@Id UUID id, String correspondingFileName) {
}
