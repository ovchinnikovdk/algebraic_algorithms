package org.task1;

import java.security.InvalidParameterException;

/**
 * Created by dmitry on 24.11.16.
 */
public class Matrix {
    private int[][] array;
    private int width;
    private int height;

    public Matrix(int size) {
        this.width = size;
        this.height = size;
        this.array = new int[size][size];
    }

    public Matrix(int height, int width) {
        this.width = width;
        this.height = height;
        this.array = new int[height][width];
    }

    public Matrix(int[][] array, int size) {
        this.array = new int[size][size];
        for (int i = 0; i < Math.min(array.length, size); i++) {
            for (int j = 0; j < Math.min(array[0].length, size); j++) {
                this.array[i][j] = array[i][j];
            }
        }
        this.width = size;
        this.height = size;
    }

    public int[][] getArray() {
        return array;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Matrix getSubMatrix(int fromX, int toX, int fromY, int toY){
        if (fromX > toX || fromY > toY) {
            throw new InvalidParameterException();
        }
        Matrix matrix = new Matrix(toX - fromX, toY - fromY);

        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                matrix.getArray()[i][j] = array[i + fromX][j + fromY];
            }
        }
        return matrix;
    }

}
