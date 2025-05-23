import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AISolutionMaxCut {

    public static Set<Integer> solve(GraphDataStructure graph) {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        Random rand = new Random();

        // Randomly assign vertices to two sets
        for (int vertex : graph.getVertices()) {
            if (rand.nextBoolean()) {
                setA.add(vertex);
            } else {
                setB.add(vertex);
            }
        }

        boolean improved = true;
        while (improved) {
            improved = false;
            for (int vertex : graph.getVertices()) {
                int cutBefore = graph.calculateCutSize(setA);

                // Move vertex to the other set
                if (setA.contains(vertex)) {
                    setA.remove(vertex);
                    setB.add(vertex);
                } else {
                    setB.remove(vertex);
                    setA.add(vertex);
                }

                int cutAfter = graph.calculateCutSize(setA);

                // Keep the change only if it improves the cut size
                if (cutAfter <= cutBefore) {
                    if (setA.contains(vertex)) {
                        setA.remove(vertex);
                        setB.add(vertex);
                    } else {
                        setB.remove(vertex);
                        setA.add(vertex);
                    }
                } else {
                    improved = true;
                }
            }
        }

        return setA; // Return the set of vertices in setA
    }

    public static void runMaxCut(GraphDataStructure graph) {
        Set<Integer> setA = solve(graph);
        Set<Integer> setB = new HashSet<>(graph.getVertices());
        setB.removeAll(setA);

        int cutSize = graph.calculateCutSize(setA);
        System.out.println("-----------------AI Solution----------------");
        System.out.println("Max Cut Size: " + cutSize);
        if (setA.size() < 100) {
            System.out.println("Best Set A: " + setA);
        } else {
            System.out.println("Best Set A: " + setA.size() + " vertices");
        }
        
        if (setB.size() < 100) {
            System.out.println("Best Set B: " + setB);
        } else {
            System.out.println("Best Set B: " + setB.size() + " vertices");
        }
    }

    public static void main(String[] args) {
        GraphDataStructure graph1 = GraphDataStructure.readFromFile("graph1.txt");
        GraphDataStructure graph2 = GraphDataStructure.readFromFile("graph2.txt");
        GraphDataStructure graph3 = GraphDataStructure.readFromFile("graph3.txt");
        GraphDataStructure graph4 = GraphDataStructure.readFromFile("graph4.txt");
        GraphDataStructure graph5 = GraphDataStructure.readFromFile("graph5.txt");

        AISolutionMaxCut.runMaxCut(graph1);
        AISolutionMaxCut.runMaxCut(graph2);
        AISolutionMaxCut.runMaxCut(graph3);
        AISolutionMaxCut.runMaxCut(graph4);
        AISolutionMaxCut.runMaxCut(graph5);
    }
}
