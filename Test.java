import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        // Test the RandomGraphGenerator
        GraphDataStructure graph1 = RandomGraphGenerator.generateRandomGraph(10, 20);
        System.out.println("Generated Graph: " + graph1.displayGraph());

                Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
        adjacencyList.put(0, Set.of(1, 2, 3, 20, 7, 23));
        adjacencyList.put(1, Set.of(0, 4, 21, 15));
        adjacencyList.put(2, Set.of(0, 18, 3, 4, 8, 9, 25, 14));
        adjacencyList.put(3, Set.of(0, 2, 22, 6, 10, 26, 15));
        adjacencyList.put(4, Set.of(1, 2, 5, 21, 6, 7, 29));
        adjacencyList.put(5, Set.of(4, 13));
        adjacencyList.put(6, Set.of(3, 4, 20, 26));
        adjacencyList.put(7, Set.of(0, 17, 4, 12));
        adjacencyList.put(8, Set.of(2, 27, 28));
        adjacencyList.put(9, Set.of(2, 11, 14));
        adjacencyList.put(10, Set.of(17, 3, 20));
        adjacencyList.put(11, Set.of(9, 14));
        adjacencyList.put(12, Set.of(16, 19, 7));
        adjacencyList.put(13, Set.of(5));
        adjacencyList.put(14, Set.of(2, 18, 9, 11, 28));
        adjacencyList.put(15, Set.of(1, 3, 25));
        adjacencyList.put(16, Set.of(19, 12));
        adjacencyList.put(17, Set.of(7, 24, 10));
        adjacencyList.put(18, Set.of(2, 14));
        adjacencyList.put(19, Set.of(16, 22, 24, 12));
        adjacencyList.put(20, Set.of(0, 6, 10));
        adjacencyList.put(21, Set.of(1, 4, 25));
        adjacencyList.put(22, Set.of(3, 19, 27));
        adjacencyList.put(23, Set.of(0));
        adjacencyList.put(24, Set.of(17, 19));
        adjacencyList.put(25, Set.of(2, 21, 15));
        adjacencyList.put(26, Set.of(3, 6));
        adjacencyList.put(27, Set.of(22, 8, 29));
        adjacencyList.put(28, Set.of(8, 14));
        adjacencyList.put(29, Set.of(4, 27));

        // GraphDataStructure graph2 = RandomGraphGenerator.generateRandomGraph(50, 80);
        // System.out.println("Generated Graph: " + graph2.displayGraph());

        // GraphDataStructure graph3 = RandomGraphGenerator.generateRandomGraph(100, 200);
        // System.out.println("Generated Graph: " + graph3.displayGraph());

        // GraphDataStructure graph4 = RandomGraphGenerator.generateRandomGraph(1000, 2000);
        // System.out.println("Generated Graph: " + graph4.displayGraph());

        // OptimisedSolution.solve(graph1);
        // OptimisedSolution.solve(graph2);
        // OptimisedSolution.solve(graph3);
        // OptimisedSolution.solve(graph4);
    
        
    
        AISolution.runMaxCut(graph1);

    }
}
