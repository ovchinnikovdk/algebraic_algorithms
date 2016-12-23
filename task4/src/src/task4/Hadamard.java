package task4;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dmitry on 23.12.16.
 */
public class Hadamard {
    private byte[][] matrix;
    private int p;
    private Set<Integer> squares;

    public Hadamard(int p) {
        this.matrix = new byte[p + 1][p + 1];
        this.p = p;
        this.squares = new HashSet<>();
        for (int i = 1; i < p; i++) {
            squares.add(i*i % p);
        }
    }

    public byte[][] generate(){
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = 1;
            matrix[0][i] = 1;
        }
        for (int i = 1; i <= this.p; i++) {
            for (int j = 1; j <= this.p; j++) {
                if (i == j) {
                    matrix[i][i] = -1;
                } else if (j - i < 0) {
                    matrix[i][j] = legendre(j - i + p);
                }
                else {
                    matrix[i][j] = legendre(j - i);
                }
            }
        }
        return this.matrix;
    }

    private byte legendre(int i){
        if (i == 0) return 0;
        if (squares.contains(i)) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
