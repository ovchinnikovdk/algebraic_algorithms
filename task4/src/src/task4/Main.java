package task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by dmitry on 23.12.16.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file = new File("answer.txt");
        int p = scanner.nextInt();
        Hadamard hadamard = new Hadamard(p);
        try (PrintWriter printWriter = new PrintWriter(file.getAbsoluteFile())) {
            byte[][] matrix = hadamard.generate();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    printWriter.write(matrix[i][j] + " ");
                }
                printWriter.write("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
