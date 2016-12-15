package task3.coverage;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dmitry on 16.12.16.
 */
public class CoverageAlgorithm {
    private Map<Integer, Vertex> vertices;
    private List<Edge> edges;

    public CoverageAlgorithm() {
        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }

    public void addEdge(int v1, int v2) throws InvalidVertexException {
        Vertex vertex1 = vertices.get(v1);
        Vertex vertex2 = vertices.get(v2);

        if (vertex1 != null && vertex2 != null) {
            Edge edge = new Edge(vertex1, vertex2);
            edges.add(edge);
        }
        else {
            throw new InvalidVertexException();
        }
    }

    public void addVertex(int v) {
        vertices.put(v, new Vertex(v));
    }

    public void setWeight(int v, int weight) throws InvalidVertexException {
        Vertex vertex = vertices.get(v);
        if (vertex != null) {
            vertex.setWeight(weight);
        }
        else {
            throw new InvalidVertexException();
        }
    }

    public Set<Integer> calculateCoverage(){
        Set<Integer> vertexSet = new HashSet<>();
        Set<Edge> edgeSet = new HashSet<>();
        edgeSet.addAll(edges);
        while (!edgeSet.isEmpty()) {
            Edge edge = edgeSet.iterator().next();
            Vertex v1 = edge.getV1();
            Vertex v2 = edge.getV2();
            if (v1.getX() < v1.getWeight() && v2.getX() < v2.getWeight()) {
                int y = Math.min(v1.getWeight() - v1.getX(), v2.getWeight() - v2.getX());
                v1.addToX(y);
                v2.addToX(y);
            }
            if (v1.getWeight() <= v1.getX() || v2.getWeight() <= v2.getX()) {
                edgeSet.remove(edge);
            }
        }
        vertexSet.addAll(vertices.values()
                .stream().filter(vertex -> vertex.getX() == vertex.getWeight())
                .map(Vertex::getId).collect(Collectors.toList()));
        return vertexSet;
    }
}
