package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 *
 * <p>click to show follow up.
 *
 * <p>Follow up: Did you use extra space? A straight forward solution using O(mn) space is probably
 * a bad idea. A simple improvement uses O(m + n) space, but still not the best solution. Could you
 * devise a constant space solution?
 */
public class SetMatrixZeroes {
    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[][] matrix = {{0, 8, 7}, {9, 0, 8}, {9, 9, 0}};
        // int[][] matrix = {{0, 1}};

        new SetMatrixZeroes().setZeroes(matrix);
        Arrays.stream(matrix).forEach(rows -> Arrays.stream(rows).forEach(System.out::println));
    }

    public void setZeroes(int[][] matrix) {
        var firstRow = false;
        for(int y=0; y<matrix[0].length; y++) {
            if (matrix[0][y] == 0) {
                firstRow = true;
                break;
            }
        }
        var firstCol = false;
        for(int x=0; x<matrix.length; x++) {
            if (matrix[x][0] == 0) {
                firstCol = true;
                break;
            }
        }
        // others
        for(int x=1; x< matrix.length; x++) {
            for(int y=1; y<matrix[0].length; y++) {
                if (matrix[x][y]==0) {
                    matrix[0][y]=0;
                    matrix[x][0]=0;
                }
            }
        }
        // set others
        for(int x=1; x< matrix.length; x++) {
            for(int y=1; y<matrix[0].length; y++) {
                if (matrix[x][0] == 0 || matrix[0][y] ==0) {
                    matrix[x][y]=0;
                }
            }
        }
        if (firstRow) {
            for(int y=0; y<matrix[0].length; y++) {
                matrix[0][y] = 0;
            }
        }
        if (firstCol) {
            for(int x=0; x<matrix.length; x++) {
                matrix[x][0] = 0;
            }
        }
    }
}
