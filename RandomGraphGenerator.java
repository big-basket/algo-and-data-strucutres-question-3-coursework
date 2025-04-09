import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RandomGraphGenerator {
    public static GraphDataStructure generateRandomGraph(int numVertices, int numEdges) {
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
        GraphDataStructure graph = new GraphDataStructure(adjacencyList);

        for (int i = 0; i < numEdges; i++) {
            int u = (int) (Math.random() * numVertices);
            int v = (int) (Math.random() * numVertices);
            if (u != v) {
                graph.addEdge(u, v);
            }
        }

        return graph;
    }
}