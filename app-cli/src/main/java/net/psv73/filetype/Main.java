package net.psv73.filetype;

import net.psv73.filetype.utils.FolderTool;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        if (args.length < 1) {
            System.out.println("Usage: run <filesFolder> [patternDbPath]");
            return;
        }

        String filesFolder = args[0];
        String patternDB = args[1];

        (new FolderTool()).searchInPatternDB(patternDB, filesFolder);
    }
}
