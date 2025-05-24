CREATE TABLE IF NOT EXISTS job_confirmation_pdf
(
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    filename VARCHAR(255),
    file BLOB NOT NULL
);
