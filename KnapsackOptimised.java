import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class KnapsackOptimised {

    private static int[][] items; // Updated to store [[value, weight], ...]
    private static int capacity;

    // Constructor
    public KnapsackOptimised(String filePath) {
        try {
            // Read the file
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Read values
            String[] valuesLine = scanner.nextLine().split(" ");
            int[] values = Arrays.stream(valuesLine).mapToInt(Integer::parseInt).toArray();

            // Read weights
            String[] weightsLine = scanner.nextLine().split(" ");
            int[] weights = Arrays.stream(weightsLine).mapToInt(Integer::parseInt).toArray();

            // Read capacity
            capacity = Integer.parseInt(scanner.nextLine());

            // Combine values and weights into a 2D array of [[value, weight], ...]
            items = new int[values.length][2];
            for (int i = 0; i < values.length; i++) {
                items[i][0] = values[i];  // First column: value
                items[i][1] = weights[i]; // Second column: weight
            }

            // Print the items for debugging
            System.out.println("Items (Value, Weight):");
            System.out.println(Arrays.deepToString(items));

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        }
    }

    // Main method
    public static void main(String[] args) {
        File currentDirectory = new File("."); // Current directory
        File[] files = currentDirectory.listFiles((dir, name) -> name.startsWith("Knapsack") && name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("No files starting with 'Knapsack' found.");
            return;
        }

        for (File file : files) {
            System.out.println("Processing file: " + file.getName());
            KnapsackOptimised knapsack = new KnapsackOptimised(file.getPath()); // Create an instance to initialize items        

            // Creates the dynamic programming table
            int[][] dpTable = knapsackDynamic(items, capacity);

            // The maximum value is stored in the last cell of the DP table
            int maxValue = dpTable[items.length][capacity];

            // Print the DP table
            System.out.println("Dynamic Programming Table:");
            for (int[] row : dpTable) {
                System.out.println(Arrays.toString(row));
            }

            // Print the maximum value
            System.out.println("Maximum Value: " + maxValue);

            // Backtrack to find the items included in the knapsack
            List<int[]> selectedItems = findSelectedItems(dpTable, items, capacity);
            System.out.println("Items included in the knapsack (Value, Weight):");
            for (int[] item : selectedItems) {
                System.out.println(Arrays.toString(item));
            }
        }
    }

    // Method to create an adjustable array
    public static int[][] knapsackDynamic(int[][] items, int capacity) {
        int n = items.length;
        int[][] dp = new int[n + 1][capacity + 1];
    
        // Build the DP table
        for (int i = 1; i <= n; i++) {
            int value = items[i - 1][0];
            int weight = items[i - 1][1];
            for (int w = 0; w <= capacity; w++) {
                if (weight <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weight] + value);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
    
        // Return the DP table
        return dp;
    }

    // Method to find the items included in the knapsack
    public static List<int[]> findSelectedItems(int[][] dp, int[][] items, int capacity) {
        List<int[]> selectedItems = new ArrayList<>();
        int n = items.length;
        int w = capacity;

        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selectedItems.add(items[i - 1]); // Add the item to the list
                w -= items[i - 1][1]; // Reduce the remaining capacity
            }
        }

        return selectedItems;
    }
}