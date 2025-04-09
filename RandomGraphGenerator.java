import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RandomGraphGenerator {
    public static GraphDataStructure generateRandomGraph(int numVertices, int numEdges) {
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
        GraphDataStructure graph = new GraphDataStructure(adjacencyList);

        for (int i = 1; i < numVertices; i++) {
            int v = (int) (Math.random() * i);
            graph.addEdge(i, v);
        }

        int remainingEdges = numEdges - (numVertices - 1);
        while (remainingEdges > 0) {
            int u = (int) (Math.random() * numVertices);
            int v = (int) (Math.random() * numVertices);
            if (u != v && !graph.hasEdge(u, v)) {
                graph.addEdge(u, v);
                remainingEdges--;
            }
        }

        return graph;
    }
}