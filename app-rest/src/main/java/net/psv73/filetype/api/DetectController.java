package net.psv73.filetype.api;

import net.psv73.filetype.SignatureDetector;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DetectController {
    private final SignatureDetector detector;
    public DetectController(SignatureDetector detector) { this.detector = detector; }

    @PostMapping(value = "/detect", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> detect(@RequestPart("file") MultipartFile file) throws IOException {
        String type = detector.detect(file.getBytes());
        return Map.of(
                "file", file.getOriginalFilename() == null ? "uploaded" : file.getOriginalFilename(),
                "size", String.valueOf(file.getSize()),
                "type", type
        );
    }
}
