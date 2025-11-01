import java.io.IOException;

import utility.InputReader;
import components.Grid;
import logic.GenerationProcessor;

public class Main {

    public static void main(String[] args) {
        // Student information
        System.out.println("-----------------------------------------");
        System.out.println(
                "| CMPE436 Assignment-3 HW1-Q2 \t\t| \n| Erdem Tuzla - 2024700114\t\t|\n| erdem.tuzla@std.bogazici.edu.tr\t|");
        System.out.println("-----------------------------------------");

        try {
            InputReader reader = new InputReader(args[0]);

            // Set grid size
            int[] gridSize = reader.readGridSize();
            int generationCount = reader.readGenerationCount();

            // Create the processor and give required generation count data
            GenerationProcessor processor = new GenerationProcessor(
                    new Grid(gridSize[0], gridSize[1], true),
                    generationCount);

            processor.process();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}