package analyzer.utils;

import analyzer.model.PatternDBRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final String DELIMITER = ";";

    public static List<PatternDBRecord> readPatternsFromCSV(String fileName) {
        List<PatternDBRecord> patterns = new ArrayList<>();
        Path file = Paths.get(fileName);

        try (
                BufferedReader br = Files.newBufferedReader(file)
        ) {
            String line = br.readLine();

            while (line != null) {

                String[] attribute = line.replace("\"", "").split(DELIMITER);

                PatternDBRecord patternDBRecord = stringToPattern(attribute);

                patterns.add(patternDBRecord);

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return patterns;
    }

    private static PatternDBRecord stringToPattern(String[] str) {
        PatternDBRecord patternDBRecord = new PatternDBRecord();

        patternDBRecord.setPriority(Integer.parseInt(str[0]));
        patternDBRecord.setPattern(str[1]);
        patternDBRecord.setFileType(str[2]);

        return patternDBRecord;
    }
}
