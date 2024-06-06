package generate.file;

import generate.utils.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Controls the input file and creates the initial list of items for each line in the input.txt
 */
public class InputFile {
    private final List<String> contents = new ArrayList<>();
    private final List<Environment> environments;
    private String taskId;

    public InputFile() throws FileNotFoundException {
        environments = new ArrayList<>();
        taskId = null;
        setContents();
    }

    private void setContents() throws FileNotFoundException {
        File file = new File("file/input.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("SD-")) {
                taskId = line;
                continue;
            }
            if (line.startsWith("--") && line.endsWith("--")) {
                environments.add(Environment.getEnvironment(line));
                continue;
            }
            contents.add(line);
        }
    }

    public List<String> getContents() {
        return contents;
    }

    public String getOutputPath() {
        return taskId != null ? "file/output/" + taskId + ".txt" : "file/output.txt";
    }

    public List<Environment> getEnvironments() {
        return environments;
    }
}
