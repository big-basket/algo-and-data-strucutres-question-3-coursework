public class knapsack {
    public static void main(String[] args) {
        // Values and weights of items
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;

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

            // Only keep the value if it's within the weight limit that's been set
            if (currentWeight <= capacity && currentValue > maxValue) {
                maxValue = currentValue;
            }
        }
        // Output the maximum value that can be carried
        System.out.println("Max value we can carry = " + maxValue);
    }
}
