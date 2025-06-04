package de.steinuntersteinen.jerpapi.repositories;

import de.steinuntersteinen.jerpapi.model.jobconfirmation.JobConfirmationPdf;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JobConfirmationPdfRepository extends CrudRepository<JobConfirmationPdf, UUID> {
}
