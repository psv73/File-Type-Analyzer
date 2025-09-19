package net.psv73.filetype.api;

import net.psv73.filetype.service.SignatureDetector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AnalyzerService {
    private final SignatureDetector detector;

    public AnalyzerService(@Value("classpath:patterns.db") Resource db) throws IOException {
        // если у тебя есть метод loadFromPath
        this.detector = new SignatureDetector();
    }

    public String analyze(byte[] fileBytes) throws IOException {
        if (fileBytes == null || fileBytes.length == 0)
            throw new IllegalArgumentException("File is empty");
        return detector.detect(fileBytes);
    }
}

