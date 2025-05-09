import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class knapsack {
    public static void main(String[] args) {
        File currentDirectory = new File("."); // Current directory
        File[] files = currentDirectory.listFiles((dir, name) -> name.startsWith("Knapsack") && name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("No files starting with 'Knapsack' found.");
            return;
        }

        for (File file : files) {
            System.out.println("Processing file: " + file.getName());

            try (Scanner scanner = new Scanner(file)) {
                // Read values
                String[] valuesLine = scanner.nextLine().split(" ");
                int[] values = Arrays.stream(valuesLine).mapToInt(Integer::parseInt).toArray();

                // Read weights
                String[] weightsLine = scanner.nextLine().split(" ");
                int[] weights = Arrays.stream(weightsLine).mapToInt(Integer::parseInt).toArray();

                // Read capacity
                int capacity = Integer.parseInt(scanner.nextLine());

                // Solve the knapsack problem using the naive approach
                int maxValue = knapsackNaive(weights, values, capacity);
                System.out.println("Maximum value in the knapsack: " + maxValue);
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + file.getPath());
            }
        }
    }

    // Method to find the maximum value of items that can be carried in the knapsack
    public static int knapsackNaive(int[] weights, int[] values, int capacity) {
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
        return maxValue;
    }
}
