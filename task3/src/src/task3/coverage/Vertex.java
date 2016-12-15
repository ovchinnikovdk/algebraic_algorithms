package task3.coverage;

/**
 * Created by dmitry on 16.12.16.
 */
public class Vertex {
    private int id;
    private int weight;
    private int x;

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getX() {
        return x;
    }

    public void addToX(int number) {
        this.x += number;
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
