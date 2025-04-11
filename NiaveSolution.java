import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NiaveSolution {
    public static Set<Integer> solve(GraphDataStructure graph) {
        Set<Integer> bestSetA = new HashSet<>();
        int maxCutSize = 0;

        List<Integer> vertices = new ArrayList<>(graph.getVertices());
        int n = vertices.size();
        int totalSubsets = 1 << n; 

        for (int mask = 0; mask < totalSubsets; mask++) {
            Set<Integer> setA = new HashSet<>();

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    setA.add(vertices.get(i));
                }
            }

            int cutSize = graph.calculateCutSize(setA);
            if (cutSize > maxCutSize) {
                maxCutSize = cutSize;
                bestSetA = new HashSet<>(setA);
            }
        }

        System.out.println("Max Cut Size: " + maxCutSize);
        if (bestSetA.size() < 100) {
            System.out.println("Best Set A: " + bestSetA);
        } else {
            System.out.println("Best Set A: " + bestSetA.size() + " vertices");
        }
        
        final Set<Integer> finalBestSetA = new HashSet<>(bestSetA);
        List<Integer> bestSetB = graph.getVertices().stream().filter(v -> !finalBestSetA.contains(v)).toList();
        if (bestSetB.size() < 100) {
            System.out.println("Best Set B: " + bestSetB);
        } else {
            System.out.println("Best Set B: " + bestSetB.size() + " vertices");
        }
        return bestSetA;
    }
}