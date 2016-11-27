package task2;

import java.util.Comparator;

/**
 * Created by dmitry on 27.11.16.
 */
public class VertexComparator implements Comparator<Vertex> {
    @Override
    public int compare(Vertex o1, Vertex o2) {
        return Integer.compare(o1.getId(), o2.getId());
    }
}
