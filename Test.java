
public class Test {
    public static void main(String[] args) {
        GraphDataStructure graph2 = GraphDataStructure.readFromFile("random_graph.txt");
        OptimisedSolutionMaxCut.solve(graph2);
    }
}
