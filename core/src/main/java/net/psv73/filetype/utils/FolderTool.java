package net.psv73.filetype.utils;

import net.psv73.filetype.model.PatternDBRecord;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FolderTool {

    private List<PatternDBRecord> patterns = new ArrayList<>();

    private static final int THREAD_POOL_SIZE = 10;
    private static final String CURRENT_DIR = System.getProperty("user.dir") + File.separator;

    public void searchInFolder(String filesFolder) {
        SearchTool searchTool = new SearchTool();

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (Path path : getAllFilesFromFolder(filesFolder)) {

            executor.submit(() -> {
                    String fileType = "Unknown file type";
                    for (PatternDBRecord pr : patterns) {
                        if (searchTool.determineFileType("--RK",
                                path,
                                pr.getPattern())) {
                            fileType = pr.getFileType();
                            break;
                        }
                    }
                System.out.println(path.getFileName() + ": " + fileType);
            });
        }

        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void searchInPatternDB(String patternDB, String folder) {
        String fileName = CURRENT_DIR + patternDB;
        patterns = Utils.readPatternsFromCSV(fileName);

        patterns.sort(Collections.reverseOrder());

        searchInFolder(folder);
    }

    private List<Path> getAllFilesFromFolder(String filesFolder) {
        try (Stream<Path> walk = Files.walk(Paths.get(CURRENT_DIR + filesFolder))) {
            return walk.filter(Files::isRegularFile).sorted().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

}
