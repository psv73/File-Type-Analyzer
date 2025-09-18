package net.psv73.filetype;

import java.nio.file.*;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println("Usage: run <filesFolder>");
            return;
        }

        Path dir = Paths.get(args[0]).toAbsolutePath().normalize();

        if (!Files.isDirectory(dir)) {
            System.out.println("Not a directory: " + dir);
            return;
        }

        SignatureDetector detector = new SignatureDetector();

        try (var stream = Files.list(dir)) {
            stream.filter(Files::isRegularFile)
                    .filter(p -> !p.getFileName().toString().startsWith("."))
                    .sorted()
                    .forEach(p -> {
                        try {
                            String t = detector.detect(p);
                            System.out.println(p.getFileName() + ": " + t);
                        } catch (Exception e) {
                            System.out.println(p.getFileName() + ": ERROR - " + e.getMessage());
                        }
                    });
        }
    }
}
