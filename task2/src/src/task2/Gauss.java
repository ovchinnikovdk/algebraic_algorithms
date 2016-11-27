package task2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dmitry on 27.11.16.
 */
public class Gauss {
    public static float EPSILON = 0.0001f;
    public static Set<Vertex> doGauss(Map<Vertex, Map<Vertex, Float>> matrix){
        Set<Vertex> result = new TreeSet<>(new VertexComparator());
        Map<Vertex, Map<Vertex, Float>> subMatrix = new TreeMap<>(new VertexComparator());
        matrix.entrySet().stream().filter(entry -> !entry.getKey().isFixed()).forEach(entry -> {
            Map<Vertex, Float> equation = new TreeMap<>(new VertexComparator());
            entry.getValue().keySet().stream().filter(vertex -> !vertex.isFixed()).forEach(vertex -> {
                equation.put(vertex, entry.getValue().get(vertex));
            });
            subMatrix.put(entry.getKey(), equation);
        });
        Vertex[] vertices = new Vertex[subMatrix.size()];
        int verticesLength = 0;
        for (Vertex vertex : subMatrix.keySet()) {
            vertices[verticesLength++] = vertex;
        }
        for (int i = 0; i < vertices.length - 1; i++) {
            for (int j = i + 1; j < vertices.length; j++) {
                float coeff = -1.0f * subMatrix.get(vertices[j]).get(vertices[i]) /
                        subMatrix.get(vertices[i]).get(vertices[i]);
                for (Map.Entry<Vertex, Float> entry : subMatrix.get(vertices[j]).entrySet()) {
                    Float value = entry.getValue();
                    value += coeff * subMatrix.get(vertices[i]).get(entry.getKey());
                    subMatrix.get(vertices[j]).put(entry.getKey(), value);
                }
            }
        }
        for (int i = vertices.length - 1; i >= 0; i--) {
            float x = 0.0f;
            float y = 0.0f;
            boolean flag = false;
            for (Map.Entry<Vertex, Float> entry : subMatrix.get(vertices[i]).entrySet()) {
                if (!entry.getKey().equals(vertices[i])) {
                    if (entry.getValue() > EPSILON) {
                        if (entry.getKey().getX() != null && entry.getKey().getY() != null) {
                            float coeff = subMatrix.get(vertices[i]).get(vertices[i]);
                            x -= entry.getKey().getX() * entry.getValue() / coeff;
                            y -= entry.getKey().getY() * entry.getValue() / coeff;
                            flag = true;
                        }
                    }
                }
            }
            for (Map.Entry<Vertex, Float> entry : matrix.get(vertices[i]).entrySet()) {
                if (entry.getKey().isFixed()) {
                    float coeff = matrix.get(vertices[i]).get(vertices[i]);
                    x -= entry.getKey().getX() * entry.getValue() / coeff;
                    y -= entry.getKey().getY() * entry.getValue() / coeff;
                    flag = true;
                }
            }
            if (flag) {
                vertices[i].setX(x);
                vertices[i].setY(y);
                result.add(vertices[i]);
            }
        }
        result.addAll(matrix.keySet().stream().filter(Vertex::isFixed).collect(Collectors.toList()));
        return result;
    }
}
