package breadth_first_search;

import java.util.*;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 * Example 1:
 *      Input:  0 0 0 0 1 0 0 0 0
 *      Output: 0 0 0 0 1 0 0 0 0
 *
 * Example 2:
 *      Input:  0 0 0 0 1 0 1 1 1
 *      Output: 0 0 0 0 1 0 1 2 1
 *
 * Note: The number of elements of the given matrix will not exceed 10,000. There are at
 * least one 0 in the given matrix. The cells are adjacent in only four directions: up,
 * down, left and right.
 *
 * Solution: Add all the 0th cell to the queue and do a multi-source bfs to count the minimum
 * distance
 */
public class Matrix {

    public static void main(String[] args) {
        int[][] temp = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] result = new Matrix().updateMatrix(temp);
        Arrays.stream(result).forEach(row -> {
            Arrays.stream(row).forEach(cell -> System.out.printf("%d ", cell));
            System.out.println();
        });
    }

    public int[][] updateMatrix(int[][] matrix) {
        // 1 <= m, n <= 10^4,  position encoding: for matrix[x][y], location = x << 14 + y
        Queue<Integer> queue = new ArrayDeque<>();

        for(int x=0; x<matrix.length; x++) {
            for(int y=0; y<matrix[0].length; y++) {
                if (matrix[x][y] != 0) {
                    continue;
                }
                int xBefore = x != 0                    ? matrix[x-1][y] : 0;
                int xAfter  = x != matrix.length    - 1 ? matrix[x+1][y] : 0;
                int yBefore = y != 0                    ? matrix[x][y-1] : 0;
                int yAfter  = y != matrix[0].length - 1 ? matrix[x][y+1] : 0;
                if (xBefore != 0 || xAfter != 0 || yBefore != 0 || yAfter != 0) {
                    queue.add((x << 14) + y);
                }
            }
        }
        int queueSize = queue.size();
        int distance=-1;
        while (! queue.isEmpty()) {
            var location = queue.poll();
            var x = location >> 14;
            var y = location & ((1 << 14) - 1);

            if (x != 0) {
                if (matrix[x-1][y] == 1) {
                    matrix[x-1][y] = distance;
                    queue.add(((x-1) << 14) + y);
                }
            }
            if (x != matrix.length - 1) {
                if (matrix[x+1][y] == 1) {
                    matrix[x+1][y] = distance;
                    queue.add(((x+1) << 14) + y);
                }
            }
            if (y != 0) {
                if (matrix[x][y-1] == 1) {
                    matrix[x][y-1] = distance;
                    queue.add((x << 14) + y-1);
                }
            }
            if (y != matrix[0].length - 1) {
                if (matrix[x][y+1] == 1) {
                    matrix[x][y+1] = distance;
                    queue.add((x << 14) + y+1);
                }
            }
            if (--queueSize == 0) {
                queueSize = queue.size();
                distance--;
            }
        }
        for(int x=0; x<matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                matrix[x][y] = -matrix[x][y];
            }
        }
        return matrix;
    }
}
