package net.psv73.filetype.config;

import net.psv73.filetype.service.SignatureAnalyzer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AnalyzerConfig {

    @Bean
    public SignatureAnalyzer signatureAnalyzer(
            @Value("${analyzer.read-limit:560}") int readLimit
    ) throws IOException {
        return new SignatureAnalyzer(readLimit);
    }
}
