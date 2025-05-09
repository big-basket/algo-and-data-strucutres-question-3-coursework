
public class Test {
    public static void main(String[] args) {
        // Test the RandomGraphGenerator
        GraphDataStructure graph1 = RandomGraphGenerator.generateRandomGraph(1000, 2000);
        System.out.println("Generated Graph: " + graph1.displayGraph());
    
        int aiBetterCount = 0;
        int optimisedBetterCount = 0;
        int equalCount = 0;

        for (int i = 0; i < 1000; i++) {
            GraphDataStructure graph = RandomGraphGenerator.generateRandomGraph(1000, 2000);
            Integer aiCutSize = AISolutionMaxCut.solve(graph);
            Integer optimisedCutSize = OptimisedSolutionMaxCut.solve(graph);

            if (aiCutSize > optimisedCutSize) {
                optimisedBetterCount++;
            } else if (aiCutSize < optimisedCutSize) {
                aiBetterCount++;
            } else {
                equalCount++;
            }
        }

        System.out.println("AI Solution performed better " + aiBetterCount + " times.");
        System.out.println("Optimised Solution performed better " + optimisedBetterCount + " times.");
        System.out.println("Both solutions were equal " + equalCount + " times.");
    }
}
