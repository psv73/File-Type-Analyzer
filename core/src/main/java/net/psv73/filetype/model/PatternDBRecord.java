package net.psv73.filetype.model;

import java.util.Objects;

public final class PatternDBRecord {
    private final String fileType;
    private final int offset;        // позиция сигнатуры (обычно 0)
    private final byte[] signature;  // разобранные байты


    public PatternDBRecord(String fileType, int offset, byte[] signature) {
        this.fileType = Objects.requireNonNull(fileType, "fileType");
        this.offset = offset;
        this.signature = Objects.requireNonNull(signature, "signature").clone();
    }

    public String getFileType()  { return fileType; }
    public int getOffset()       { return offset; }
    public byte[] getSignature() { return signature.clone(); }

    // Supported formats:
    //   TYPE;OFFSET;HEX HEX ...
    //   TYPE;HEX HEX ...        // offset=0
    public static PatternDBRecord parse(String line) {
        String[] p = line.trim().split(";");
        if (p.length < 2) {
            throw new IllegalArgumentException("Bad patterns.db line: " + line);
        }

        String type = p[0].trim();
        boolean hasOffset = p.length >= 3 && p[1].trim().matches("-?\\d+");

        int offset = hasOffset ? Integer.parseInt(p[1].trim()) : 0;

        String hex = (hasOffset ? p[2] : p[1]).trim();

        int expectedLen = hasOffset ? 3 : 2;

        if (p.length > expectedLen) {
            throw new IllegalArgumentException("Unexpected extra tokens: " + line);
        }

        return new PatternDBRecord(type, offset, hexToBytes(hex));
    }

    private static byte[] hexToBytes(String hex) {
        String[] t = hex.trim().replaceAll("\\s+", " ").split(" ");
        byte[] out = new byte[t.length];
        for (int i = 0; i < t.length; i++) {
            out[i] = (byte) Integer.parseInt(t[i], 16);
        }
        return out;
    }
}
