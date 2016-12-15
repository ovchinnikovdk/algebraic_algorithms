package task2;

import java.util.Scanner;
import java.util.Set;

/**
 * Created by dmitry on 27.11.16.
 */
public class Main {
    public static int FIXED_VERTICES_NUMBER = 3;
    public static float[][] FIXED_COORDINATES = {{0.0f, 0.0f}, {1.0f, 0.0f}, {1.0f, 1.0f}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Graph g = new Graph();
        int verticesCount = in.nextInt();
        int edgesCount = in.nextInt();
        int[] fixed = new int[FIXED_VERTICES_NUMBER];
        for (int i = 0; i < FIXED_VERTICES_NUMBER; i++) {
            fixed[i] = in.nextInt();
            Vertex v = new Vertex(fixed[i], true);
            v.setX(FIXED_COORDINATES[i][0]);
            v.setY(FIXED_COORDINATES[i][1]);
            g.addVertex(v);
        }
        for (int i = 1; i <= verticesCount; i++) {
            boolean flag = false;
            for (int j = 0; j < FIXED_VERTICES_NUMBER; j++) {
                if (i == fixed[j]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                Vertex v = new Vertex(i, false);
                g.addVertex(v);
            }
        }
        for (int i = 0; i < edgesCount; i++) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            g.addEdge(v1, v2);
        }
        try {
            Set<Vertex> vertices = g.drawGraph();
            vertices.stream().forEach(vertex -> System.out.println(vertex.getX() + " " + vertex.getY()));
        } catch (FixedVerticesNotPlaneException e) {

        }
    }
}
