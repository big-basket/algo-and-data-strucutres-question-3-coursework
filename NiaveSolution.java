import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NiaveSolution {
    public static Set<Integer> solve(GraphDataStructure graph) {
        Set<Integer> bestSetA = new HashSet<>();
        int maxCutSize = 0;

        List<Integer> vertices = new ArrayList<>(graph.getVertices());
        Collections.sort(vertices);
        int n = vertices.size();
        int totalSubsets = 1 << n; 
        int printEvery = Math.max(1, totalSubsets / 10);

        System.out.println("-----------------Niave Solution----------------");
        
        if (n >= 31 || totalSubsets <= 0) {
            System.out.println("Integer overflow in totalSubsets. Skipping niave solution.");
            return Collections.emptySet();
        }

        for (int mask = 0; mask < totalSubsets; mask++) {
            if (mask % printEvery == 0) {
                System.out.println("Processing mask: " + mask + "/" + totalSubsets);
            }
            
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
        
        Set<Integer> bestSetB = new HashSet<>(graph.getVertices());
        bestSetB.removeAll(bestSetA);

        if (bestSetB.size() < 100) {
            System.out.println("Best Set B: " + bestSetB);
        } else {
            System.out.println("Best Set B: " + bestSetB.size() + " vertices");
        }

        return bestSetA;
    }
}