package breadth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 17/03/2019 On a 2x3 board, there are 5 tiles represented by the
 * integers 1 through 5, and an empty square represented by 0.
 *
 * <p>A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * <p>The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * <p>Given a puzzle board, return the least number of moves required so that the state of the board
 * is solved. If it is impossible for the state of the board to be solved, return -1.
 *
 * <p>Examples:
 *
 * <p>Input: board = [[1,2,3],[4,0,5]] Output: 1 Explanation: Swap the 0 and the 5 in one move.
 * Input: board = [[1,2,3],[5,4,0]] Output: -1 Explanation: No number of moves will make the board
 * solved. Input: board = [[4,1,2],[5,0,3]] Output: 5 Explanation: 5 is the smallest number of moves
 * that solves the board. An example path: After move 0: [[4,1,2],[5,0,3]] After move 1:
 * [[4,1,2],[0,5,3]] After move 2: [[0,1,2],[4,5,3]] After move 3: [[1,0,2],[4,5,3]] After move 4:
 * [[1,2,0],[4,5,3]] After move 5: [[1,2,3],[4,5,0]] Input: board = [[3,2,4],[1,5,0]] Output: 14
 * Note:
 *
 * <p>board will be a 2 x 3 array as described above. board[i][j] will be a permutation of [0, 1, 2,
 * 3, 4, 5].
 *
 * <p>Solution: Do a bfs of each state of the board to find the least possible moves.
 */
public class SlidingPuzzle {

    public static void main(String[] args) {
        int[][] board = {{1, 2, 3}, {4, 0, 5}};
        System.out.println(new SlidingPuzzle().slidingPuzzle(board));
    }

    public int slidingPuzzle(int[][] board) {
        // Using bit encoding, Each number need 4 bits + 4 bits for 0 field indicator
        final int zeroIdxMask = 0x0f;
        final int zeroIdxMaskOut = ~zeroIdxMask;
        final int[] fieldMask = {0xf0, 0xf00, 0xf000, 0xf_0000, 0xf0_0000, 0xf00_0000};
        final int[] fieldMaskOut = {~0xf0, ~0xf00, ~0xf000, ~0xf_0000, ~0xf0_0000, ~0xf00_0000};
        final int[] fieldShift = {4, 8, 12, 16, 20, 24};
        final int[][] moveMap = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        final int target = 0x0543210 | 5;
        int val = 0;
        for(int x=0; x<board.length; x++) {
            for (int y=0; y<board[0].length; y++) {
                var loc = x*board[0].length + y;
                if (board[x][y] == 0) {
                    val |= loc;
                }
                val |= board[x][y] << fieldShift[loc];
            }
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(val);
        int queueSize = queue.size();
        int moveCnt = 0;
        while (! queue.isEmpty()) {
            val = queue.poll();
            if (val == target) {
                return moveCnt;
            }
            visited.add(val);
            int zeroIdx = val & 0xf;
            for (var otherIdx: moveMap[zeroIdx]) {
                int otherVal = ((val & fieldMask[otherIdx]) >> fieldShift[otherIdx]) << fieldShift[zeroIdx];
                int next = (val & zeroIdxMaskOut) & fieldMaskOut[otherIdx] | otherVal | otherIdx;
                if (! visited.contains(next)) {
                    queue.add(next);
                }
            }
            if (--queueSize == 0) {
                queueSize = queue.size();
                moveCnt++;
            }
        }
        return -1;
    }
}
