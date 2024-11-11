package depth_first_search;

import java.util.*;

/**
 * An image is represented by an m x n integer grid image where image[i][j] represents
 * the pixel value of the image.
 *
 * You are also given three integers sr, sc, and color. You should perform a flood fill
 * on the image starting from the pixel image[sr][sc].
 *
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally
 * to the starting pixel of the same color as the starting pixel, plus any pixels connected
 * 4-directionally to those pixels (also with the same color), and so on. Replace the color of
 * all of the aforementioned pixels with color.
 *
 * Return the modified image after performing the flood fill.
 *
 * Example 1:
 *      Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 *      Output: [[2,2,2],[2,2,0],[2,0,1]]
 *      Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel),
 *                  all pixels connected by a path of the same color as the starting pixel
 *                  (i.e., the blue pixels) are colored with the new color.
 *                  Note the bottom corner is not colored 2, because it is not 4-directionally
 *                  connected to the starting pixel.
 *
 * Example 2:
 *      Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
 *      Output: [[0,0,0],[0,0,0]]
 *      Explanation: The starting pixel is already colored 0, so no changes are made to the image.
 */
public class FloodFill {

    public static void main(String[] args) {
        var ret = new FloodFill().floodFill(
                new int[][]{{1,1,1},{1,1,0},{1,0,1}}, 1, 1, 2
        );
        Arrays.stream(ret).forEach(raw -> {
            Arrays.stream(raw).forEach(cell -> System.out.printf("%d ", cell));
            System.out.println("");
        });
        // FloodFill.MakeChange(135);
        //
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        final int[][] moves = {{-1,0}, {1,0}, {0, -1}, {0, 1}};
        int srcColor = image[sr][sc];
        Queue<Long> queue = new ArrayDeque<>();
        queue.add(((long)sr) << 32 | sc);
        while(! queue.isEmpty()) {
            var loc = queue.poll();
            var locX = (int) (loc >> 32);
            var locY = loc.intValue();
            image[locX][locY] = color;
            for (int[] move : moves) {
                var x = locX + move[0];
                var y = locY + move[1];
                if (x < 0 || x >= image.length || y < 0 || y >= image[0].length) {
                    continue;
                }
                if (image[x][y] == srcColor) {
                    queue.add(((long) x) << 32 | y);
                }
            }
        }
        return image;
    }

    public static int MakeChange(int amount)
    {
        final int[] values = {100, 20, 10, 5, 2, 1};

        int count = 0;
        int valueIdx = 0;
        while (amount > 0) {
            count += amount / values[valueIdx];
            amount %= values[valueIdx];
            valueIdx ++;
        }
        return count;
    }

    public int[][] floodFill2(int[][] image, int sr, int sc, int color) {
        final int[][] MOVE = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        var source = image[sr][sc];
        if (source != color) {
            int[] queue = new int[image.length * image[0].length];
            int queueIdx = 0;
            queue[queueIdx++] = (sr << 16) + sc;  // bit encoding
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
        return image;
    }
}
