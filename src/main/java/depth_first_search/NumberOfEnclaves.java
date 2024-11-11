package depth_first_search;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by gouthamvidyapradhan on 31/07/2019 Given a 2D array A, each cell is 0 (representing
 * sea) or 1 (representing land)
 *
 * <p>A move consists of walking from one land square 4-directionally to another land square, or off
 * the boundary of the grid.
 *
 * <p>Return the number of land squares in the grid for which we cannot walk off the boundary of the
 * grid in any number of moves.
 *
 * <p>Example 1:
 *
 * <p>Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]] Output: 3 Explanation: There are three 1s
 * that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary. Example 2:
 *
 * <p>Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]] Output: 0 Explanation: All 1s are either on
 * the boundary or can reach the boundary.
 *
 * <p>Note:
 *
 * <p>1 <= A.length <= 500 1 <= A[i].length <= 500 0 <= A[i][j] <= 1 All rows have the same size.
 * Solution O(N x M) Do a dfs to count number of enclaves - in each dfs check if it violates the
 * condition to be considered a enclave.
 */
public class NumberOfEnclaves {

    public static void main(String[] args) {
        //int[][] A = {{0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}};
        int[][] A = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        System.out.println(new NumberOfEnclaves().numEnclaves(A));
    }

    public int numEnclaves(int[][] grid) {
        // change color from 1 to 0 from edge
        int queue[] = new int[grid.length * grid[0].length];

        for(int y=0; y < grid[0].length; y++) {
            if (grid[0][y] == 1) {
                this.floodFill(grid, queue, 0, y, 0);
            }
            if (grid[grid.length-1][y] == 1) {
                this.floodFill(grid, queue, grid.length-1, y, 0);
            }
        }
        for(int x=0; x < grid.length; x++) {
            if (grid[x][0] == 1) {
                this.floodFill(grid, queue, x, 0, 0);
            }
            if (grid[x][grid[0].length-1] == 1) {
                this.floodFill(grid, queue, x, grid[0].length-1, 0);
            }
        }
        int ret = 0;
        for(int x=1; x < grid.length-1; x++) {
            for(int y=1; y < grid[0].length-1; y++) {
                ret += grid[x][y];
            }

        }
        return ret;
    }

    public void floodFill(int[][] image, int[] queue, int sr, int sc, int color) {
        final int[][] MOVE = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        var source = image[sr][sc];
        if (source != color) {
            int queueIdx=0;
            queue[queueIdx++] = (sr << 16) + sc;
            while (queueIdx != 0) {
                var loc = queue[--queueIdx];
                var curX = loc >> 16;
                var curY = loc & 0xffff;
                image[curX][curY] = color;
                for (int i = 0; i < MOVE.length; i++) {
                    var x = curX + MOVE[i][0];
                    var y = curY + MOVE[i][1];
                    if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && image[x][y] == source) {
                        queue[queueIdx++] = (x << 16) + y;
                    }
                }
            }
        }
    }
}
