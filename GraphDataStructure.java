import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
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
        if (adjacencyList.size() > 100) {
            return "Graph contains " + adjacencyList.size() + " vertices and " + getEdgeCount() + " edges. Too large to display.";
        } else {
            return adjacencyList.toString();
        }
    }

    public boolean hasEdge(int u, int v) {
        return adjacencyList.containsKey(u) && adjacencyList.get(u).contains(v);
    }

    // New method to write the graph to a file
    public void writeToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Map.Entry<Integer, Set<Integer>> entry : adjacencyList.entrySet()) {
                writer.write(entry.getKey() + " -> " + entry.getValue() + System.lineSeparator());
            }
            System.out.println("Graph written to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static GraphDataStructure readFromFile(String filePath) {
    Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
    GraphDataStructure graph = new GraphDataStructure(adjacencyList);

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" -> ");
            if (parts.length != 2) continue;

            int vertex = Integer.parseInt(parts[0].trim());
            String neighborsString = parts[1].replaceAll("[\\[\\]\\s]", "");
            if (!neighborsString.isEmpty()) {
                String[] neighbors = neighborsString.split(",");
                for (String neighbor : neighbors) {
                    int neighborVertex = Integer.parseInt(neighbor.trim());
                    graph.addEdge(vertex, neighborVertex);
                }
            }
        }
        System.out.println("Graph loaded from file: " + filePath);
    } catch (IOException e) {
        System.err.println("Error reading from file: " + e.getMessage());
    }

    return graph;
}
}
