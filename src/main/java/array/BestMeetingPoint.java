package array;

/**
 * A group of two or more people wants to meet and
 * minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks
 * the home of someone in the group. The distance is calculated using Manhattan Distance, where
 * distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 * <p>
 * Example:
 * Input: {{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}
 * Output: 6
 * <p>
 * Explanation: Given three people living at (0,0), (0,4), and (2,2): The point (0,2) is an ideal
 * meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
 * <p>
 * Solution: O(N ^ 2 + M ^ 2) + O(N x M): Calculate the total number of persons in each row and
 * each column and then take a minimum of cartesian product of each row and each column.
 */
public class BestMeetingPoint {

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        System.out.println(new BestMeetingPoint().minTotalDistance(grid));
    }

    /*
    Input: int[][] grid, Output: total travel distance

    Total x travel, target x, cal min dis
    Total y travel, target y, cal min dis
     */

    public int minTotalDistance(int[][] grid) {
        var xWeight = new int[grid.length];
        var yWeight = new int[grid[0].length];

        for (int x=0; x<grid.length; x++) {
            for(int y=0; y<grid[0].length; y++) {
                if (grid[x][y] != 0) {
                    xWeight[x]++;
                    yWeight[y]++;
                }
            }
        }
        int minXDis = Integer.MAX_VALUE;
        for (int c=0; c<grid.length; c++) {
            int total = 0;
            for(int x=0; x<c; x++) {
                total += xWeight[x] * (c-x);
            }
            for (int x=c+1; x< grid.length; x++) {
                total += xWeight[x] * (x-c);
            }
            minXDis = Math.min(minXDis, total);
        }
        int minYDis = Integer.MAX_VALUE;
        for (int c=0; c<grid[0].length; c++) {
            int total = 0;
            for (int y=0; y<c; y++) {
                total += yWeight[y] * (c-y);
            }
            for (int y=c+1; y < grid[0].length; y++) {
                total += yWeight[y] * (y-c);
            }
            minYDis = Math.min(minYDis, total);
        }

        return minXDis + minYDis;
    }

    public int minTotalDistance2(int[][] grid) {
        var xWeight = new int[grid.length];
        var yWeight = new int[grid[0].length];

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                xWeight[x] += grid[x][y];
                yWeight[y] += grid[x][y];
            }
        }

        int xMin = Integer.MAX_VALUE;
        for (int c = 0; c < grid.length; c++) {
            int total = 0;
            for (int x = 0; x < grid.length; x++) {
                total += Math.abs(c - x) * xWeight[x];
            }
            xMin = Math.min(xMin, total);
        }

        int yMin = Integer.MAX_VALUE;
        for (int c = 0; c < grid[0].length; c++) {
            int total = 0;
            for (int y = 0; y < grid[0].length; y++) {
                total += Math.abs(c - y) * yWeight[y];
            }
            yMin = Math.min(yMin, total);
        }
        return xMin + yMin;
    }
}
