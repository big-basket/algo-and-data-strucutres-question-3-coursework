import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphDataStructure {
    private final Map<Integer, Set<Integer>> adjacencyList;

    public GraphDataStructure(Map<Integer, Set<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new HashSet<>());
    }

    public void addEdge(int u, int v) {
        addVertex(u);
        addVertex(v);
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u);
    }

    public Set<Integer> getVertices() {
        return adjacencyList.keySet();
    }

    public Set<Integer> getNeighbors(int vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptySet());
    }

    public int calculateCutSize(Set<Integer> setA) {
        int cutEdges = 0;
        for (int u : setA) {
            for (int v : adjacencyList.getOrDefault(u, Collections.emptySet())) {
                if (!setA.contains(v)) {
                    cutEdges++;
                }
            }
        }
        return cutEdges;
    }

    public int getEdgeCount() {
        int count = 0;
        for (int u : adjacencyList.keySet()) {
            count += adjacencyList.get(u).size();
        }
        return count / 2; 
    }

    public String displayGraph() {
        return adjacencyList.toString();
    }

}
