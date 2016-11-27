package task2;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dmitry on 26.11.16.
 */
public class Vertex {
    private final int id;
    private final boolean fixed;
    private final Set<Vertex> connections;
    private Float x;
    private Float y;

    public Vertex(int id, boolean fixed) {
        this.id = id;
        this.fixed = fixed;
        connections = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public boolean isFixed() {
        return fixed;
    }

    public Float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void addConnection(Vertex vertex){
        this.connections.add(vertex);
    }

    public int getDegree(){
        return this.connections.size();
    }

    public boolean isConnectedTo(Vertex vertex){
        return this.connections.contains(vertex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return id == vertex.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
