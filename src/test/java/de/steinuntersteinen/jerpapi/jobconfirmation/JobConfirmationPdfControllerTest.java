package de.steinuntersteinen.jerpapi.jobconfirmation;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class JobConfirmationPdfControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldStorePdfFileInDatabase() throws Exception {

        Path testFile = Paths.get("src/test/resources/dummy.pdf");
        String testFileName = testFile.getFileName().toString();

        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                testFileName,
                "application/pdf",
                Files.readAllBytes(testFile)
        );

        mockMvc.perform(multipart("/api/jobconfirmations/upload").file(mockFile))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void shouldReturnBadRequestWhenFileIsNotPdf() throws Exception {
        Path testFile = Paths.get("src/test/resources/notAPdfDummy.txt");
        String testFileName = testFile.getFileName().toString();

        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                testFileName,
                "application/pdf",
                Files.readAllBytes(testFile)
        );

        mockMvc.perform(multipart("/api/jobconfirmations/upload").file(mockFile))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenContentTypeIsNotApplicationPdf() throws Exception {
        Path testFile = Paths.get("src/test/resources/dummy.pdf");
        String testFileName = testFile.getFileName().toString();

        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                testFileName,
                "application/json",
                Files.readAllBytes(testFile)
        );

        mockMvc.perform(multipart("/api/jobconfirmations/upload").file(mockFile))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnJobConfirmationPdfMetadata(@Autowired JdbcTemplate jdbcTemplate) {
        JobConfirmationPdf testRecord = new JobConfirmationPdf(
                UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
                "testfile.pdf",
                "MockData".getBytes(StandardCharsets.UTF_8)
        );

        jdbcTemplate.update("""
                    INSERT INTO job_confirmation_pdf (id, filename, file)
                    VALUES (?, ?, ?)""",
                testRecord.id(), testRecord.filename(), testRecord.file()
        );

        ResponseEntity<String> response = restTemplate.getForEntity(
                "/api/jobconfirmations/123e4567-e89b-12d3-a456-426614174000",
                String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        UUID id = UUID.fromString(documentContext.read("$.id"));
        assertThat(id).isEqualTo(testRecord.id());

        String filename = documentContext.read("$.filename");
        assertThat(filename).isEqualTo(testRecord.filename());
    }

}