package archive;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateLoanOrigChecklist {
    public static void main(String[] args) {
        File inputFile = new File("input.txt");
        File outputFile = new File("output.txt");
        try {
            Scanner inputScanner = new Scanner(inputFile);
            FileWriter outputFileWriter = new FileWriter(outputFile);
            clearOutput(outputFileWriter);

            List<String> input = getContents(inputScanner);
            List<?> output = writeOutput(outputFileWriter, input);
            print(input, output);
            outputFileWriter.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<String> getContents(Scanner inputScanner) {
        List<String> contents = new ArrayList<>();
        while (inputScanner.hasNextLine()) {
            String line = inputScanner.nextLine();
            contents.add(line);
        }
        return contents;
    }

    private static List<String> writeOutput(FileWriter outputFileWriter, List<String> input) throws IOException {
        List<String> output = parseOutput(input);
        for (String line : output) {
            outputFileWriter.write(line + "\n");
        }
        return output;
    }

    private static List<String> parseOutput(List<String> input) {
        List<String> output = new ArrayList<>();
        int count = 1;
        for (int lineNumber = 0; lineNumber < input.size(); lineNumber++) {
            String line = input.get(lineNumber);
            if (input.get(lineNumber).isEmpty()) continue;

            if (lineNumber == 0 || (input.get(lineNumber - 1).isEmpty())) {
                output.add(String.format("%d) %s", count++, line));
            }
            else {
                output.add(String.format("- %s", line));
            }
        }
        return output;
    }

    private static void clearOutput(FileWriter outputFileWriter) throws IOException {
        outputFileWriter.write("");
    }

    private static void print(List<?> input, List<?> output) {
        String spacer = "----------------------------------";

        System.out.println("-----Input-----");
        input.forEach(System.out::println);
        System.out.println(spacer);

        System.out.println("-----Output-----");
        output.forEach(System.out::println);
        System.out.println(spacer);
    }
}
