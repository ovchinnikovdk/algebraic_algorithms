package org.task1;

import java.security.InvalidParameterException;

/**
 * Created by dmitry on 24.11.16.
 */
public class MatrixUtils {

    public static int STRASSEN_ARRAY_MIN_SIZE = 8;

    public Matrix sum(Matrix m1, Matrix m2) {
        if (m1.getHeight() != m2.getHeight()) {
            throw new InvalidParameterException("Height: " + m1.getHeight() + " != " + m2.getHeight());
        }
        if (m1.getWidth() != m2.getWidth()) {
            throw new InvalidParameterException("Width: " + m1.getHeight() + " != " + m2.getHeight());
        }
        Matrix matrix = new Matrix(m1.getHeight(), m1.getWidth());
        int[][] array = matrix.getArray();
        int[][] array1 = m1.getArray();
        int[][] array2 = m2.getArray();
        for (int i = 0; i < m1.getHeight(); i++) {
            for (int j = 0; j < m1.getWidth(); j++) {
                array[i][j] = array1[i][j] + array2[i][j];
            }
        }
        return matrix;
    }

    public Matrix sub(Matrix m1, Matrix m2){
        if (m1.getHeight() != m2.getHeight()) {
            throw new InvalidParameterException("Height: " + m1.getHeight() + " != " + m2.getHeight());
        }
        if (m1.getWidth() != m2.getWidth()) {
            throw new InvalidParameterException("Width: " + m1.getHeight() + " != " + m2.getHeight());
        }
        Matrix matrix = new Matrix(m1.getHeight(), m1.getWidth());
        int[][] result = matrix.getArray();
        int[][] array1 = m1.getArray();
        int[][] array2 = m2.getArray();
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                result[i][j] = array1[i][j] - array2[i][j];
            }
        }
        return matrix;
    }

    public Matrix scale(Matrix m, int x) {
        Matrix matrix = new Matrix(m.getHeight(), m.getWidth());
        int[][] result = matrix.getArray();
        int[][] array1 = m.getArray();
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                result[i][j] = array1[i][j] * x;
            }
        }
        return matrix;
    }

    public Matrix hstack(Matrix m1, Matrix m2) {
        if (m1.getHeight() != m2.getHeight()) {
            throw new InvalidParameterException(m1.getHeight() + " != " + m2.getHeight());
        }
        Matrix matrix = new Matrix(m1.getHeight(), m1.getWidth() + m2.getWidth());
        int[][] result = matrix.getArray();
        int[][] array1 = m1.getArray();
        int[][] array2 = m2.getArray();
        for (int i = 0 ; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                result[i][j] = j < m1.getWidth() ? array1[i][j] : array2[i][j - m1.getWidth()];
             }
        }
        return matrix;
    }

    public Matrix vstack(Matrix m1, Matrix m2) {
        if (m1.getWidth() != m2.getWidth()) {
            throw new InvalidParameterException(m1.getHeight() + " != " + m2.getHeight());
        }
        Matrix matrix = new Matrix(m1.getHeight() + m2.getHeight(), m1.getWidth());
        int[][] result = matrix.getArray();
        int[][] array1 = m1.getArray();
        int[][] array2 = m2.getArray();
        for (int i = 0 ; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                result[i][j] = i < m1.getHeight() ? array1[i][j] : array2[i - m1.getHeight()][j];
            }
        }
        return matrix;
    }

    public Matrix simpleMultiply(Matrix m1, Matrix m2) {
        if (m2.getHeight() != m1.getWidth()) {
            throw new InvalidParameterException();
        }
        Matrix matrix = new Matrix(m1.getHeight(), m2.getWidth());
        int[][] result = matrix.getArray();
        int[][] array1 = m1.getArray();
        int[][] array2 = m2.getArray();
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                for (int k = 0; k < m1.getWidth(); k++) {
                    result[i][j] += array1[i][k] * array2[k][j];
                }
            }
        }
        return matrix;
    }

    public Matrix vinogradStrassenMultiply(Matrix m1, Matrix m2) {
        if (m1.getWidth() != m2.getHeight()) {
            throw new InvalidParameterException();
        }
        if (m1.getHeight() < MatrixUtils.STRASSEN_ARRAY_MIN_SIZE) {
            return simpleMultiply(m1, m2);
        }
        else {
            int size = m1.getHeight();
            if (m1.getHeight() % 2 != 0) {
                    m1 = vstack(hstack(m1, new Matrix(size, 1)), new Matrix(1, size + 1));
                    m2 = vstack(hstack(m2, new Matrix(size, 1)), new Matrix(1, size + 1));
            }
            int actualSize = m1.getHeight();
            Matrix a11 = m1.getSubMatrix(0, actualSize/2, 0, actualSize/2);
            Matrix a12 = m1.getSubMatrix(actualSize/2, actualSize, 0, actualSize/2);
            Matrix a21 = m1.getSubMatrix(0, actualSize/2, actualSize/2, actualSize);
            Matrix a22 = m1.getSubMatrix(actualSize/2, actualSize, actualSize/2, actualSize);
            Matrix b11 = m2.getSubMatrix(0, actualSize/2, 0, actualSize/2);
            Matrix b12 = m2.getSubMatrix(actualSize/2, actualSize, 0, actualSize/2);
            Matrix b21 = m2.getSubMatrix(0, actualSize/2, actualSize/2, actualSize);
            Matrix b22 = m2.getSubMatrix(actualSize/2, actualSize, actualSize/2, actualSize);

            Matrix s1 = sum(a21, a22);
            Matrix s2 = sub(s1, a11);
            Matrix s3 = sub(a11, a21);
            Matrix s4 = sub(a12, s2);
            Matrix s5 = sub(b12, b11);
            Matrix s6 = sub(b22, s5);
            Matrix s7 = sub(b22, b12);
            Matrix s8 = sub(s6, b21);

            Matrix p1 = vinogradStrassenMultiply(s2, s6);
            Matrix p2 = vinogradStrassenMultiply(a11, b11);
            Matrix p3 = vinogradStrassenMultiply(a12, b21);
            Matrix p4 = vinogradStrassenMultiply(s3, s7);
            Matrix p5 = vinogradStrassenMultiply(s1, s5);
            Matrix p6 = vinogradStrassenMultiply(s4, b22);
            Matrix p7 = vinogradStrassenMultiply(a22, s8);

            Matrix t1 = sum(p1, p2);
            Matrix t2 = sum(t1, p4);

            Matrix c11 = sum(p2, p3);
            Matrix c12 = sum(t1, sum(p5, p6));
            Matrix c21 = sub(t2, p7);
            Matrix c22 = sum(t2, p5);

            int newSize = size / 2 + size % 2;
            Matrix result = vstack(
                    hstack(c11.getSubMatrix(0, newSize, 0, newSize), c21.getSubMatrix(0, newSize, 0, newSize)),
                    hstack(c12.getSubMatrix(0, newSize, 0, newSize), c22.getSubMatrix(0, newSize, 0, newSize))
            );
            return result.getSubMatrix(0, size, 0, size);
        }
    }
}
