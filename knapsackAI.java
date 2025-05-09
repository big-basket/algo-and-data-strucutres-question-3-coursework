import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class knapsackAI {
    /**
     * Solves the 0/1 Knapsack problem using dynamic programming.
     * Each item can either be included entirely or excluded.
     *
     * @param weights  An array of the weights of the items.
     * @param values   An array of the values of the items.
     * @param capacity The maximum weight capacity of the knapsack.
     * @return The maximum total value that can be put into the knapsack.
     */
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // Build the dp table in a bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }

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

                // Solve the knapsack problem
                int maxValue = knapsack(weights, values, capacity);
                System.out.println("Maximum value in the knapsack: " + maxValue);
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + file.getPath());
            }
        }
    }
}
