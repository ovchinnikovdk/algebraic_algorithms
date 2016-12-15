package task3;

import task3.coverage.CoverageAlgorithm;
import task3.coverage.InvalidVertexException;

import java.util.Scanner;
import java.util.Set;

/**
 * Created by dmitry on 16.12.16.
 */
public class Main {
    public static void main(String[] args) throws InvalidVertexException {
        CoverageAlgorithm algorithm = new CoverageAlgorithm();
        Scanner in = new Scanner(System.in);
        int vertexCount = in.nextInt();
        int edgeCount = in.nextInt();
        for (int i = 1; i <= vertexCount; i++) {
            algorithm.addVertex(i);
            int weight = in.nextInt();
            algorithm.setWeight(i, weight);
        }

        for (int i = 1; i <= edgeCount; i++) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            algorithm.addEdge(v1, v2);
        }

        Set<Integer> result = algorithm.calculateCoverage();

        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}
