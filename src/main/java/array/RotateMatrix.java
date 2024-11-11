package array;


/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 *      Input: matrix = [
 *          [1,2,3],
 *          [4,5,6],
 *          [7,8,9]]
 *      Output: [
 *          [7,4,1],
 *          [8,5,2],
 *          [9,6,3]]
 *
 * Example 2:
 *      Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 *      Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 */
import java.util.Arrays;

public class RotateMatrix {
    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        /* int[][] matrix = {{5,1,9,11},
                     {2,4,8,10},
                     {13,3,6,7},
                     {15,14,12,16}};
        */
        new RotateMatrix().rotate(matrix);
        RotateMatrix.print(matrix);

        double subtotal = 100.0;
        double tax = subtotal * 0.0085;  // 8.5%
        double tip = subtotal * 0.18;  // 15% for tip
        double total = subtotal + tax + tip;
        System.out.printf("%15s: 123456789012\n", "- position -");
        System.out.printf("%15s: %12.2f\n", "Subtotal", subtotal);
        System.out.printf("%15s: %12.2f\n", "Tax", tax);
        System.out.printf("%15s: %12.2f\n", "Tip", tip);
        System.out.printf("%15s: %12.2f\n", "Total", total);
        System.out.printf("%15s: %-12.2f\n", "Lift align", total);

    }

    public static void print(int[][] matrix) {
        Arrays.stream(matrix).forEach(row -> {
            Arrays.stream(row).forEach(cell -> System.out.printf("%2d ", cell));
            System.out.println("");
        });
    }

    public void rotate(int[][] matrix) {
        if (matrix == null) {
            return;
        }

        // step 1 -> use the diagnal line to do the swap
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j > i) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
        // RotateMatrix.print(matrix);
        // RotateMatrix.print(matrix);
        // step 2 -> use the diagnal line to do the swap
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j < matrix[0].length / 2) {
                    int len = matrix[0].length; // len = 3
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[i][len - 1 - j];
                    matrix[i][len - 1 - j] = temp;
                }
            }
        }
    }
}