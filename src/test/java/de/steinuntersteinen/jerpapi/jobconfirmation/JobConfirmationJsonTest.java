package de.steinuntersteinen.jerpapi.jobconfirmation;

import de.steinuntersteinen.jerpapi.model.jobconfirmation.JobConfirmationPdf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
class JobConfirmationJsonTest {

    @Autowired
    private JacksonTester<JobConfirmationPdf> json;

    @Test
    void jobConfirmationSerializationTest() throws IOException {

        Path testFile = Paths.get("src/test/resources/dummy.pdf");
        String testFileName = testFile.getFileName().toString();

        JobConfirmationPdf jobConfirmationPdf = new JobConfirmationPdf(
                UUID.fromString("a759981c-611e-4367-abcd-9f319b273949"),
                testFileName,
                Files.readAllBytes(testFile)
        );

        assertThat(json.write(jobConfirmationPdf))
                .isStrictlyEqualToJson("expected.json");
    }

    @Test
    void jobConfirmationDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": "a759981c-611e-4367-abcd-9f319b273949",
                    "filename": "dummy.pdf",
                    "file": ""
                }
                """;

        assertThat(json.parse(expected)).isEqualTo(
                new JobConfirmationPdf(
                        UUID.fromString("a759981c-611e-4367-abcd-9f319b273949"),
                        "dummy.pdf",
                        null
                ));
    }

}