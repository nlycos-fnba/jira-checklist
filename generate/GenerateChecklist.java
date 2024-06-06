package generate;

import generate.file.InputFile;
import generate.file.OutputFile;

public class GenerateChecklist {
    public static void main(String[] args) {
        try {
            InputFile inputFile = new InputFile();
            OutputFile defaultOutput = new OutputFile("output.txt");
            OutputFile outputFile = new OutputFile(inputFile.getOutputPath());

            outputFile.generateOutputFile(inputFile.getContents(), inputFile.getEnvironments());
            defaultOutput.generateOutputFile(inputFile.getContents(), inputFile.getEnvironments());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
