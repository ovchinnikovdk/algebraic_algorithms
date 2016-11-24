package org.task1;

import java.util.Scanner;

/**
 * Created by dmitry on 24.11.16.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Matrix a = new Matrix(n);
        Matrix b = new Matrix(n);
        int[][] aArray = a.getArray();
        int[][] bArray = b.getArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aArray[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bArray[i][j] = in.nextInt();
            }
        }
        MatrixUtils utils = new MatrixUtils();
        Matrix matrix = utils.vinogradStrassenMultiply(a,b);
        int[][] result = matrix.getArray();
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
