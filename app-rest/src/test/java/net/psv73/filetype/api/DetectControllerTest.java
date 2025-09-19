package net.psv73.filetype.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DetectControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void detectsMultipleFiles() throws Exception {
        var pdf = new MockMultipartFile(
                "files", "a.pdf", "application/pdf",
                new byte[]{'%', 'P', 'D', 'F', '-'});
        var jpg = new MockMultipartFile(
                "files", "b.jpg", "image/jpeg",
                new byte[]{(byte)0xFF, (byte)0xD8, (byte)0xFF});

        mvc.perform(multipart("/api/detect").file(pdf).file(jpg))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['a.pdf']").exists())
                .andExpect(jsonPath("$.['b.jpg']").exists());
    }

    @Test
    void emptyFileReturns400() throws Exception {
        var empty = new MockMultipartFile("files","x.bin","application/octet-stream", new byte[0]);
        mvc.perform(multipart("/api/detect").file(empty))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("File is empty"));
    }
}