package net.psv73.filetype.config;

import net.psv73.filetype.service.SignatureDetector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class DetectorConfig {

    @Bean
    public SignatureDetector signatureDetector(
            @Value("${detector.read-limit:560}") int readLimit
    ) throws IOException {
        return new SignatureDetector(readLimit);
    }
}
