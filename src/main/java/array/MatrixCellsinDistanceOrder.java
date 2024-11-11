package array;

import java.util.*;

/**
 * You are given four integers row, cols, rCenter, and cCenter. There is a rows x cols matrix
 * and you are on the cell with the coordinates (rCenter, cCenter).
 *
 * Return the coordinates of all cells in the matrix, sorted by their distance from
 * (rCenter, cCenter) from the smallest distance to the largest distance. You may return the
 * answer in any order that satisfies this condition.
 *
 * The distance between two cells (r1, c1) and (r2, c2) is |r1 - r2| + |c1 - c2|.
 *
 * Example 1:
 *      Input: rows = 1, cols = 2, rCenter = 0, cCenter = 0
 *      Output: [[0,0],[0,1]]
 *      Explanation: The distances from (0, 0) to other cells are: [0,1]
 *
 * Example 2:
 *      Input: rows = 2, cols = 2, rCenter = 0, cCenter = 1
 *      Output: [[0,1],[0,0],[1,1],[1,0]]
 *      Explanation: The distances from (0, 1) to other cells are: [0,1,1,2] The answer
 *      [[0,1],[1,1],[0,0],[1,0]] would also be accepted as correct.
 *
 * Example 3:
 *      Input: rows = 2, cols = 3, rCenter = 1, cCenter = 2
 *      Output: [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 *      Explanation: The distances from (1, 2) to other cells are: [0,1,1,2,2,3] There are other
 *      answers that would also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].
 *
 * Solution: O (log (R x C)) Straight forward solution, find the Manhattan distance from the
 * given cell to all the cells in the grid and sort by min distance and return their coordinates.
 */

public class MatrixCellsinDistanceOrder {
    public static void main(String[] args) {
        var ret = new MatrixCellsinDistanceOrder().allCellsDistOrder(2, 3, 1, 2);
        // var ret = new MatrixCellsinDistanceOrder().allCellsDistOrder(2, 2, 0, 1);
        for (var cell: ret) {
            System.out.printf("%d %d\n", cell[0], cell[1]);
        }
        //
    }

    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        var ret = new int[rows * cols][2];
        var dis = new int[rows][cols];

        for (int x=0; x < rows; x++) {
            for (int y=0; y < cols; y++) {
                ret[x * cols + y][0] = x;
                ret[x * cols + y][1] = y;
                dis[x][y] = Math.abs(x - rCenter) + Math.abs(y - cCenter);
            }
        }
        return Arrays.stream(ret).sorted(Comparator.comparingInt(c -> dis[c[0]][c[1]])).toList().toArray(new int[ret.length][]);
    }
}
