package de.steinuntersteinen.jerpapi.jobconfirmation;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public record JobConfirmation(@Id UUID id, String correspondingFileName) {
}
