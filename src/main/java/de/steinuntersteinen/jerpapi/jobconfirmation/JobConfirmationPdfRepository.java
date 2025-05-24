package de.steinuntersteinen.jerpapi.jobconfirmation;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface JobConfirmationPdfRepository extends CrudRepository<JobConfirmationPdf, UUID> {
}
