package array;

/**
 * Given a 2D integer matrix M representing the gray
 * scale of an image, you need to design a smoother to make the gray scale of each cell becomes the
 * average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less
 * than 8 surrounding cells, then use as many as you can.
 *
 * <p>Example 1: Input: [[1,1,1], [1,0,1], [1,1,1]] Output: [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
 * Explanation: For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0 For the point
 * (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0 For the point (1,1): floor(8/9) =
 * floor(0.88888889) = 0 Note: The value in the given matrix is in the range of [0, 255]. The length
 * and width of the given matrix are in the range of [1, 150].
 */
public class ImageSmoother {

    int[] R = {1, -1, 0, 0, 1, -1, 1, -1};
    int[] C = {0, 0, -1, 1, 1, 1, -1, -1};

    public static void main(String[] args) {
        /*
        var result = new ImageSmoother().imageSmoother(new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        });
         */
        var result = new ImageSmoother().imageSmoother(new int[][]{
                {100, 200, 100},    // {137, 141, 137},
                {200,  50, 200},    // {141, 138, 141},
                {100, 200, 100}     // {137, 141, 137},
        });

        for (var row: result) {
            for (var cell: row) {
                System.out.printf("%3d ", cell);
            }
            System.out.println();
        }
    }

    public int[][] imageSmoother(int[][] matric) {
        int[][] result = new int[matric.length][matric[0].length];

        for (int x=1; x < matric.length-1; x++) {
            for (int y=1; y < matric[0].length-1; y++) {
                result[x - 1][y - 1] += matric[x][y];
                result[x - 1][y] += matric[x][y];
                result[x - 1][y + 1] += matric[x][y];
                result[x][y-1] += matric[x][y];
                result[x][y] += matric[x][y];
                result[x][y+1] += matric[x][y];
                result[x + 1][y - 1] += matric[x][y];
                result[x + 1][y] += matric[x][y];
                result[x + 1][y + 1] += matric[x][y];
            }
        }
        for (int y=1; y < matric[0].length-1; y++) {
            result[0][y-1] += matric[0][y];
            result[0][y] += matric[0][y];
            result[0][y+1] += matric[0][y];
            result[1][y - 1] += matric[0][y];
            result[1][y] += matric[0][y];
            result[1][y + 1] += matric[0][y];

            result[matric.length-2][y-1] += matric[matric.length-1][y];
            result[matric.length-2][y] += matric[matric.length-1][y];
            result[matric.length-2][y+1] += matric[matric.length-1][y];
            result[matric.length-1][y - 1] += matric[matric.length-1][y];
            result[matric.length-1][y] += matric[matric.length-1][y];
            result[matric.length-1][y + 1] += matric[matric.length-1][y];
        }
        for (int x=1; x < matric.length-1; x++) {
            result[x - 1][0] += matric[x][0];
            result[x - 1][1] += matric[x][0];
            result[x][0] += matric[x][0];
            result[x][1] += matric[x][0];
            result[x + 1][0] += matric[x][0];
            result[x + 1][1] += matric[x][0];

            result[x - 1][matric[0].length - 2] += matric[x][matric[0].length - 1];
            result[x - 1][matric[0].length - 1] += matric[x][matric[0].length - 1];
            result[x][matric[0].length - 2] += matric[x][matric[0].length - 1];
            result[x][matric[0].length - 1] += matric[x][matric[0].length - 1];
            result[x + 1][matric[0].length - 2] += matric[x][matric[0].length - 1];
            result[x + 1][matric[0].length - 1] += matric[x][matric[0].length - 1];
        }
        result[0][0] += matric[0][0];
        result[1][0] += matric[0][0];
        result[0][1] += matric[0][0];
        result[1][1] += matric[0][0];

        result[0][matric[0].length - 2] += matric[0][matric[0].length -1];
        result[1][matric[0].length - 2] += matric[0][matric[0].length -1];
        result[0][matric[0].length - 1] += matric[0][matric[0].length -1];
        result[1][matric[0].length - 1] += matric[0][matric[0].length -1];

        result[matric.length-2][0] += matric[matric.length-1][0];
        result[matric.length-1][0] += matric[matric.length-1][0];
        result[matric.length-1][1] += matric[matric.length-1][0];
        result[matric.length-2][1] += matric[matric.length-1][0];

        result[matric.length-2][matric[0].length-2] += matric[matric.length-1][matric[0].length -1];
        result[matric.length-1][matric[0].length-2] += matric[matric.length-1][matric[0].length -1];
        result[matric.length-2][matric[0].length-1] += matric[matric.length-1][matric[0].length -1];
        result[matric.length-1][matric[0].length-1] += matric[matric.length-1][matric[0].length -1];

        for (int x=1; x < matric.length-1; x++) {
            for (int y = 1; y < matric[0].length - 1; y++) {
                result[x][y] /= 9;
            }
        }
        for (int y=1; y < matric[0].length-1; y++) {
            result[0][y] /= 6;
            result[matric.length-1][y] /= 6;
        }
        for (int x=1; x < matric.length-1; x++) {
            result[x][0] /= 6;
            result[x][matric[0].length-1] /= 6;
        }
        result[0][0] /= 4;
        result[0][matric[0].length -1] /= 4;
        result[matric.length-1][0] /= 4;
        result[matric.length-1][matric[0].length -1] /= 4;
        return result;
    }
}
