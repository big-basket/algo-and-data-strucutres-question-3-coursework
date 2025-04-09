public class Test {
    public static void main(String[] args) {
        // Test the RandomGraphGenerator
        GraphDataStructure graph = RandomGraphGenerator.generateRandomGraph(10, 20);
        System.out.println("Generated Graph: " + graph.displayGraph());

        // OptimisedSolution optimisedSolution = new OptimisedSolution();
        // optimisedSolution.solve(graph);

    
        // NiaveSolution niaveSolution = new NiaveSolution();
        // niaveSolution.solve(graph);

    
        // AISolution aiSolution = new AISolution();
        // aiSolution.solve(graph);
    }
    
}
