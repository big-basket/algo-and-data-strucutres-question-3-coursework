import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class KnapsackBruteForce {

    public static void main(String[] args) {
        File currentDirectory = new File(".");
        File[] files = currentDirectory.listFiles((dir, name) -> name.startsWith("Knapsack") && name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("No files starting with 'Knapsack' found.");
            return;
        }

        for (File file : files) {
            System.out.println("Processing file: " + file.getName());
            try (Scanner scanner = new Scanner(file)) {
                if (!scanner.hasNextLine()) {
                    System.out.println("Error: Empty file.");
                    continue;
                }
                String[] valuesStr = scanner.nextLine().split(" ");
                int[] values = Arrays.stream(valuesStr).mapToInt(Integer::parseInt).toArray();

                if (!scanner.hasNextLine()) {
                    System.out.println("Error: Weights not found.");
                    continue;
                }
                String[] weightsStr = scanner.nextLine().split(" ");
                int[] weights = Arrays.stream(weightsStr).mapToInt(Integer::parseInt).toArray();

                if (!scanner.hasNextLine()) {
                    System.out.println("Error: Capacity not found.");
                    continue;
                }
                int capacity = Integer.parseInt(scanner.nextLine());

                if (values.length != weights.length) {
                    System.out.println("Error: Number of values and weights do not match.");
                    continue;
                }

                int itemCount = values.length;
                int maxValue = 0;

                // Try all combinations of items
                for (int i = 0; i < (1 << itemCount); i++) {
                    int currentWeight = 0;
                    int currentValue = 0;

                    for (int j = 0; j < itemCount; j++) {
                        // Check if we include item j in this combination
                        if ((i & (1 << j)) > 0) {
                            currentWeight += weights[j];
                            currentValue += values[j];
                        }
                    }

                    // Only keep the value if it's within the weight limit
                    if (currentWeight <= capacity && currentValue > maxValue) {
                        maxValue = currentValue;
                    }
                }
                // Output the maximum value that can be carried for the current file
                System.out.println("Maximum value for " + file.getName() + ": " + maxValue + "\n");

            } catch (FileNotFoundException e) {
                System.err.println("Error: File not found - " + file.getName());
            }
        }

        System.out.println("Finished processing all Knapsack files.");
    }
}