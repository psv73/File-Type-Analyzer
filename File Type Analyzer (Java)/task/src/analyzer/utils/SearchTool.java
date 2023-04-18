package analyzer.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SearchTool {

    public boolean determineFileType(String method, Path path, String pattern) {
        boolean found = false;

        try {
            String text = new String(Files.readAllBytes(path));
            SearchMethods methods = new SearchMethods();

            found = switch (method.trim()) {
                case "--naive" -> methods.methodNaive(pattern, text);
                case "--KMP" -> methods.methodKMP(pattern, text);
                case "--RK" -> methods.methodRK(pattern, text);
                default -> false;
            };

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return found;
    }
}
