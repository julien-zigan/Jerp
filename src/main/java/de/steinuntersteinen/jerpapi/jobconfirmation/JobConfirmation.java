package de.steinuntersteinen.jerpapi.jobconfirmation;

import org.springframework.data.annotation.Id;

public record JobConfirmation(@Id Long id, String correspondingFileName) {
}
