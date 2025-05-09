import java.util.*;

public class OptimisedSolutionMaxCut {

    private static final int GREEDY_IMPROVE_INTERVAL = 10; // every N accepted moves

    public static Integer solve(GraphDataStructure graph) {
        long startTime = System.currentTimeMillis();

        Set<Integer> currentSolution = new HashSet<>();
        Set<Integer> bestSolution = new HashSet<>();
        Random rand = new Random();

        for (int vertex : graph.getVertices()) {
            if (rand.nextBoolean()) {
                currentSolution.add(vertex);
            }
        }

        bestSolution.addAll(currentSolution);
        int currentCutSize = graph.calculateCutSize(currentSolution);
        int bestCutSize = currentCutSize;

        System.out.println("Initial cut size: " + currentCutSize);

        double temperature = 1000;
        double coolingRate = 0.01;

        List<Integer> vertexList = new ArrayList<>(graph.getVertices());

        vertexList.sort((v1, v2) -> graph.getNeighbors(v2).size() - graph.getNeighbors(v1).size());

        int iteration = 0;
        int acceptedMoves = 0;

        while (temperature > 1) {
            iteration++;
            Set<Integer> newSolution = new HashSet<>(currentSolution);

            int vertexToFlip = vertexList.get(rand.nextInt(Math.min(5, vertexList.size())));

            if (newSolution.contains(vertexToFlip)) {
                newSolution.remove(vertexToFlip);
            } else {
                newSolution.add(vertexToFlip);
            }

            int newCutSize = graph.calculateCutSize(newSolution);

            if (acceptanceProbability(currentCutSize, newCutSize, temperature) > rand.nextDouble()) {
                currentSolution = newSolution;
                currentCutSize = newCutSize;
                acceptedMoves++;

                System.out.println("Accepted worse/better move at iteration " + iteration + " | Temp: " + temperature + " | Cut size: " + currentCutSize);

                if (acceptedMoves % GREEDY_IMPROVE_INTERVAL == 0) {
                    long greedyStart = System.currentTimeMillis();
                    currentSolution = greedyImproveIncremental(graph, currentSolution);
                    currentCutSize = graph.calculateCutSize(currentSolution);
                    long greedyEnd = System.currentTimeMillis();
                    System.out.println("Greedy improvement time: " + (greedyEnd - greedyStart) + " ms | New cut size: " + currentCutSize);
                }
            }

            if (currentCutSize > bestCutSize) {
                bestSolution = new HashSet<>(currentSolution);
                bestCutSize = currentCutSize;
                System.out.println("New best solution found at iteration " + iteration + ": " + bestCutSize);
            }

            if (iteration % 100 == 0) {
                System.out.println("Iteration " + iteration + " | Temp: " + temperature + " | Best cut: " + bestCutSize);
            }

            temperature *= 1 - coolingRate;
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("-----------------Improved Optimised Solution----------------");
        System.out.println("Best Cut Size: " + bestCutSize);
        if (bestSolution.size() < 100) {
            System.out.println("Best Set A: " + bestSolution);
        } else {
            System.out.println("Best Set A: " + bestSolution.size() + " vertices");
        }

        Set<Integer> bestSetB = new HashSet<>(graph.getVertices());
        bestSetB.removeAll(bestSolution);

        if (bestSetB.size() < 100) {
            System.out.println("Best Set B: " + bestSetB);
        } else {
            System.out.println("Best Set B: " + bestSetB.size() + " vertices");
        }

        System.out.println("Execution Time: " + duration + " ms");
        return graph.calculateCutSize(bestSolution);
    }

    private static double acceptanceProbability(int currentCost, int newCost, double temperature) {
        if (newCost > currentCost) return 1.0;
        return Math.exp((newCost - currentCost) / temperature);
    }

    private static Set<Integer> greedyImproveIncremental(GraphDataStructure graph, Set<Integer> currentSetA) {
        Set<Integer> setA = new HashSet<>(currentSetA);
        Set<Integer> setB = new HashSet<>(graph.getVertices());
        setB.removeAll(setA);

        boolean improved = true;
        int innerIterations = 0;

        Map<Integer, Set<Integer>> neighborsMap = new HashMap<>();
        for (int v : graph.getVertices()) {
            neighborsMap.put(v, graph.getNeighbors(v));
        }

        while (improved) {
            improved = false;
            for (int vertex : graph.getVertices()) {
                innerIterations++;

                boolean inA = setA.contains(vertex);
                Set<Integer> neighbors = neighborsMap.get(vertex);

                int cutChange = 0;
                for (int neighbor : neighbors) {
                    boolean neighborInA = setA.contains(neighbor);
                    if (inA != neighborInA) cutChange--;  
                    else cutChange++; 
                }

                if (cutChange > 0) {
                    if (inA) {
                        setA.remove(vertex);
                        setB.add(vertex);
                    } else {
                        setB.remove(vertex);
                        setA.add(vertex);
                    }
                    improved = true;
                }
            }
        }

        System.out.println("Greedy improvement iterations: " + innerIterations);
        return setA;
    }
}
