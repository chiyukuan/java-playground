package array;

/**
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could
 * be horizontal, vertical, diagonal or anti-diagonal.
 *
 * Example:
 *      Input: [[0,1,1,0], [0,1,1,0], [0,0,0,1]]
 *      Output: 3
 *      Hint: The number of elements in the given matrix will not exceed 10,000.
 *
 * Solution O(N x M) for each cell keep track of maximum value possible horizontally, vertically
 * and diagonally. Start iterating from left-right and top-bottom and repeat the same from
 * right-left and top to bottom to get max for anti-diagonal and return the max value.
 */
public class LongestLineofConsecutiveOneinMatrix {
    final int[] R = {0, 0, -1, 1};
    final int[] C = {1, -1, 0, 0};

    public static void main(String[] args) {
        int[][] M = {
                {1, 1, 0, 0, 1, 0, 0, 1, 1, 0},
                {1, 0, 0, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 0, 1},
                {1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
                {0, 1, 0, 1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 0, 1, 1, 1, 1}
        };
        System.out.println(new LongestLineofConsecutiveOneinMatrix().longestLine(M));
    }

    public int longestLine(int[][] matrix) {
        var lkp = new int[matrix.length][matrix[0].length][4]; // for directions: |, -. \, /

        var max = 0;
        for (int x=0; x < matrix.length; x++) {
            for (int y=0; y < matrix.length; y++) {
                if (matrix[x][y] == 0) continue;

                if (x != 0) {
                    lkp[x][y][0] = 1 + lkp[x - 1][y][0];
                    max = Math.max(max, lkp[x][y][0]);

                    if (y != 0) {
                        lkp[x][y][2] = 1 + lkp[x - 1][y - 1][2];
                        max = Math.max(max, lkp[x][y][2]);
                    }

                    if (y != matrix[0].length - 1) {
                        lkp[x][y][3] = 1 + lkp[x - 1][y + 1][3];
                        max = Math.max(max, lkp[x][y][3]);
                    }
                }
                if (y != 0) {
                    lkp[x][y][1] = 1 + lkp[x][y - 1][1];
                    max = Math.max(max, lkp[x][y][1]);
                }
            }

        }
        return max;
    }
}
