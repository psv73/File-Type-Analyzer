package net.psv73.filetype;

import net.psv73.filetype.service.SignatureAnalyzer;

import java.nio.file.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.err.println("Usage: app-cli <file-or-dir> [more...]");
            System.exit(1);
        }

        Path dir = Paths.get(args[0]).toAbsolutePath().normalize();

        SignatureAnalyzer analyzer = new SignatureAnalyzer();

        for (String a : args) {
            Path p = Paths.get(a);

            if (Files.isRegularFile(p)) {
                print(analyzer, p);
            } else if (Files.isDirectory(p)) {
                try (Stream<Path> s = Files.list(p)) {
                    s.filter(Files::isRegularFile).forEach(f -> print(analyzer, f));
                }
            } else {
                System.err.println("Not found: " + p);
            }
        }
    }

    private static void print(SignatureAnalyzer analyzer, Path file) {
        try {
            byte[] bytes = Files.readAllBytes(file);
            String type = analyzer.analyze(file);
            System.out.println(file.getFileName() + ": " + type);
        } catch (Exception e) {
            System.err.println("Error: " + file + " → " + e.getMessage());
        }
    }
}
