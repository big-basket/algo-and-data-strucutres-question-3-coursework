
public class Test {
    public static void main(String[] args) {
        // Test the RandomGraphGenerator
        GraphDataStructure graph1 = RandomGraphGenerator.generateRandomGraph(30, 60);
        System.out.println("Generated Graph: " + graph1.displayGraph());

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
    
        NiaveSolution.solve(graph1);
        
    
        AISolution.runMaxCut(graph1);

    }
}
