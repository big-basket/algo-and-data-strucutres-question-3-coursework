public class knapsackAI {
    /**
     * Solves the 0/1 Knapsack problem using dynamic programming.
     * Each item can either be included entirely or excluded.
     *
     * @param weights An array of the weights of the items.
     * @param values  An array of the values of the items.
     * @param capacity The maximum weight capacity of the knapsack.
     * @return The maximum total value that can be put into the knapsack.
     */
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        // dp[i][w] stores the maximum value that can be obtained with a knapsack of
        // capacity w using the first i items.
        int[][] dp = new int[n + 1][capacity + 1];

        // Build the dp table in a bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                // If the current item's weight is less than or equal to the current capacity
                if (weights[i - 1] <= w) {
                    // We have two choices:
                    // 1. Include the current item: value[i-1] + value obtained from the remaining capacity
                    // 2. Exclude the current item: value obtained from the previous items with the same capacity
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    // If the current item's weight is more than the current capacity, we cannot include it
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // The maximum value that can be put into the knapsack is at dp[n][capacity]
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;

        int maxValue = knapsack(weights, values, capacity);
        System.out.println("Maximum value in the knapsack: " + maxValue); // Output: 220

        int[] values2 = {1, 2, 3};
        int[] weights2 = {4, 5, 1};
        int capacity2 = 4;

        int maxValue2 = knapsack(weights2, values2, capacity2);
        System.out.println("Maximum value in the knapsack: " + maxValue2); // Output: 3
    }
}
