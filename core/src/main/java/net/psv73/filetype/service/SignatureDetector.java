package net.psv73.filetype.service;

import net.psv73.filetype.model.PatternDBRecord;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class SignatureDetector {

    private static final String PATTERN_DB = "patterns.db";
    private static final int DEFAULT_READ_LIMIT = 560;

    private final List<PatternDBRecord> patterns;

    public SignatureDetector() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(PATTERN_DB)) {
            if (is == null) throw new FileNotFoundException(PATTERN_DB + " not found on classpath");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                this.patterns = parseDb(br);
            }
        }
    }

    public String detect(Path path) throws IOException {
        byte[] head = readHead(path, DEFAULT_READ_LIMIT);
        return detect(head);
    }

    public String detect(byte[] data) {
        for (PatternDBRecord r : patterns) {
            if (matchesAt(data, r.getSignature(), r.getOffset())) return r.getFileType();
        }
        return "Unknown file type";
    }

    // ==== helpers ====
    private static List<PatternDBRecord> parseDb(BufferedReader br) throws IOException {
        List<PatternDBRecord> out = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; ) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;
            out.add(PatternDBRecord.parse(line));
        }

        return out;
    }

    private static boolean matchesAt(byte[] data, byte[] sig, int offset) {
        if (offset < 0 || offset + sig.length > data.length) return false;
        for (int i = 0; i < sig.length; i++) if (data[offset + i] != sig[i]) return false;
        return true;
    }

    private static byte[] readHead(Path p, int n) throws IOException {
        try (InputStream in = Files.newInputStream(p)) {
            byte[] buf = new byte[n];
            int read = in.read(buf);
            return read == n ? buf : Arrays.copyOf(buf, Math.max(0, read));
        }
    }
}
