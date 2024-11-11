package depth_first_search;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or
 * vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *      Input: grid = [
 *          ['1','1','1','1',"0"],
 *          ['1','1',"0",'1',"0"],
 *          ['1','1',"0","0","0"],
 *          ["0","0","0","0","0"]
 *      ]
 *      Output: 1
 *
 * Example 2:
 *      Input: grid = [
 *          ['1','1',"0","0","0"],
 *          ['1','1',"0","0","0"],
 *          ["0","0",'1',"0","0"],
 *          ["0","0","0",'1','1']
 *      ]
 *      Output: 3
 *
 * Constraints:
 *      - m == grid.length
 *      - n == grid[i].length
 *      - 1 <= m, n <= 300
 *      - grid[i][j] is '0' or '1'.
 */
public class NumberOfIslands {

    public static void main(String args[]) {
        var ret = new NumberOfIslands().numIslands(new char[][]{
                 {'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1',
                  '1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1',
                  '1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1',
                  '1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1',
                  '1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1',
                  '1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1',
                  '1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1',
                  '1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1',
                  '1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1',
                  '1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1',
                  '1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1',
                  '1','1','1','1','1','1','1','1','1','1','1','1'}
                /*
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
                 */
        });
    }

    public int numIslands(char[][] grid) {
        // change color from 1 to 0 from edge
        int queue[] = new int[grid.length * grid[0].length];

        int ret = 0;
        char target = '0';
        for(int x=0; x < grid.length; x++) {
            for(int y=0; y < grid[0].length; y++) {
                if (grid[x][y] == '1') {
                    this.floodFill(grid, queue, x, y, target);
                    ret++;
                }
            }
        }
        return ret;
    }

    public void floodFill(char[][] image, int[] queue, int sr, int sc, char color) {
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
