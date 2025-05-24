package de.steinuntersteinen.jerpapi.jobconfirmation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class JobConfirmationPdfControllerTest {

    @Test
    void shouldStorePdfFileInDatabase(@Autowired MockMvc mvc) throws Exception {

        Path testFile = Paths.get("src/test/resources/dummy.pdf");
        String testFileName = testFile.getFileName().toString();

        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                testFileName,
                "application/pdf",
                Files.readAllBytes(testFile)
        );

        mvc.perform(multipart("/api/jobconfirmations/upload").file(mockFile))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

}