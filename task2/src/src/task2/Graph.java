package task2;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dmitry on 26.11.16.
 */
public class Graph {
    private Set<Vertex> vertices;

    public Graph() {
        this.vertices = new TreeSet<>(new VertexComparator());
    }

    public void addVertex(Vertex vertex){
        vertices.add(vertex);
    }

    public void addEdge(int id1, int id2){
        Vertex v1 = this.vertices.stream().filter(vertex -> vertex.getId() == id1).findFirst().get();
        Vertex v2 = this.vertices.stream().filter(vertex -> vertex.getId() == id2).findFirst().get();
        v1.addConnection(v2);
        v2.addConnection(v1);
    }

    public Set<Vertex> drawGraph() throws FixedVerticesNotPlaneException {
        Set<Vertex> fixed = getFixedVertices();
        if (!isTheSamePlane(fixed)) {
            throw new FixedVerticesNotPlaneException();
        }
        Map<Vertex, Map<Vertex, Float>> laplasian = new TreeMap<>(new VertexComparator());
        for (Vertex v1 : vertices) {
            laplasian.put(v1, new TreeMap<>(new VertexComparator()));
            for (Vertex v2 : vertices) {
                if (v1.equals(v2)) {
                    laplasian.get(v1).put(v1, (float)v1.getDegree());
                }
                else if (v1.isConnectedTo(v2)) {
                    laplasian.get(v1).put(v2, -1.0f);
                }
                else {
                    laplasian.get(v1).put(v2, 0.0f);
                }
            }
        }
        return Gauss.doGauss(laplasian);
    }

    private Set<Vertex> getFixedVertices(){
        return this.vertices.stream().filter(Vertex::isFixed).collect(Collectors.toSet());
    }

    private boolean isTheSamePlane(Set<Vertex> fixedVertices){
        boolean flag = false;
        Vertex first = fixedVertices.iterator().next();
        Vertex previous = first;
        for (Vertex vertex : fixedVertices) {
            if (vertex.equals(previous)) {
                continue;
            }
            for (Vertex vertex1 : fixedVertices) {
                if (previous.isConnectedTo(vertex1)) {
                    flag = true;
                    previous = vertex;
                    break;
                }
            }
            if (flag) {
                flag = false;
            }
            else {
                return false;
            }
        }
        return previous.isConnectedTo(first);
    }

}
