CREATE TABLE IF NOT EXISTS job_confirmation_pdf
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    filename VARCHAR(255),
    file BLOB NOT NULL
);
