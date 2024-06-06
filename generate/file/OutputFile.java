package generate.file;

import generate.utils.App;
import generate.utils.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls the output file and formats data to be the proper output
 */
public class OutputFile {
    private final List<String> contents = new ArrayList<>();
    private final FileWriter fileWriter;

    public OutputFile(String filePath) throws IOException {
        File file = new File(filePath);
        this.fileWriter = new FileWriter(file);
    }

    /**
     * Generates the desired output file based off of the input file
     * @param input List of each line from the input file
     * @param environmentList List of each environment that the checklist will generate for
     * @throws IOException
     */
    public void generateOutputFile(List<String> input, List<Environment> environmentList) throws IOException {
        this.setContents(input, environmentList);
        this.writeOutput();
        this.printResults();
    }

    private void writeOutput() throws IOException {
        for (String line : contents) {
            fileWriter.write(line + "\n");
        }
        fileWriter.close();
    }

    private void setContents(List<String> input, List<Environment> environmentList) throws IOException {
        this.clearOutput();
        for (Environment environment : environmentList) {
            contents.addAll(parseContents(input, environment.getEnvironmentName()));
        }
    }

    private List<String> parseContents(List<String> input, String environment) {
        List<String> output = new ArrayList<>();
        output.add(String.format("**%s**", environment));
        int count = 1;
        for (int lineNumber = 0; lineNumber < input.size(); lineNumber++) {
            if (input.get(lineNumber).isEmpty()) continue;
            String line = input.get(lineNumber);

            if (line.startsWith("__") && line.endsWith("__")) {
                App app = App.getApp(line);
                output.add(String.format("%d) Go to the %s %s application at: %s", count++, environment, app.getAppName(), app.getLink(environment)));
            }
            else if (lineNumber == 0 || (input.get(lineNumber - 1).isEmpty())) {
                output.add(String.format("%d) %s", count++, line));
            }
            else {
                output.add(String.format("- %s", line));
            }
        }
        return output;
    }

    private void clearOutput() throws IOException {
        fileWriter.write("");
    }

    private void printResults() {
        String spacer = "------------------------------";
        System.out.println(spacer);
        for (String line : contents) {
            System.out.println(line);
        }
        System.out.println(spacer);
    }
}
