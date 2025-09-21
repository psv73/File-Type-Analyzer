package net.psv73.filetype.api;

import net.psv73.filetype.service.SignatureAnalyzer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AnalyzerController {

    private final SignatureAnalyzer analyzer;
    private final AnalyzerService service;

    public AnalyzerController(SignatureAnalyzer analyzer, AnalyzerService service) {
        this.analyzer = analyzer;
        this.service = service;
    }

    @PostMapping(value = "/analyze", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> analyzer(@RequestPart("files") MultipartFile[] files) throws IOException {

        Map<String,String> result = new LinkedHashMap<>();

        for (MultipartFile f : files) {
            result.put(f.getOriginalFilename(), service.analyze(f.getBytes()));
        }

        return result;
    }

    @GetMapping("/read-limit")
    public int getLimit() {
        return analyzer.getReadLimit();
    }
}
