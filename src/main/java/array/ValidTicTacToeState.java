package array;

/**
 * Given a Tic-Tac-Toe board as a string array board, return true if and only if it is
 * possible to reach this board position during the course of a valid tic-tac-toe game.
 * The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The ' '
 * character represents an empty square.
 *
 * Here are the rules of Tic-Tac-Toe:
 *  - Players take turns placing characters into empty squares ' '.
 *  - The first player always places 'X' characters, while the second player always places 'O' characters.
 *  - 'X' and 'O' characters are always placed into empty squares, never filled ones.
 *  - The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
 *  - The game also ends if all squares are non-empty.
 *  - No more moves can be played if the game is over.
 *
 * Example 1:
 *      Input: board = ["O  ","   ","   "]
 *      Output: false
 *      Explanation: The first player always plays "X".
 *
 * Example 2:
 *      Input: board = ["XOX"," X ","   "]
 *      Output: false
 *      Explanation: Players take turns making moves.
 *
 * Example 3:
 *      Input: board = ["XOX","O O","XOX"]
 *      Output: true
 *
 * Constraints:
 *      - board.length == 3
 *      - board[i].length == 3
 *      - board[i][j] is either 'X', 'O', or ' '.
 */
public class ValidTicTacToeState {

    public static void main(String[] args) {
        String[] board = {"XXX", "XOO", "OO "};
        System.out.println(new ValidTicTacToeState().validTicTacToe(board));
    }

    // valid boards:
    // - 'X' count = 'O' count + 1 or 'X' count == 'O' count
    // - Only one three of same character filling any row, column, diagonal.
    //      - if 'X' => 'X' count = 'O' count + 1
    //      - if 'O' => 'X' count == 'O' count
    // - invalid wins
    public boolean validTicTacToe(String[] board) {
        int[] masks = new int[]{
                0b000_000_111, 0b000_111_000, 0b111_000_000,
                0b100_100_100, 0b010_010_010, 0b001_001_001,
                0b100_010_001, 0b001_010_100
        };
        int[] invalidWin = new int[]{ // only need to concern 2 wins
                0b000_000_110, 0b00_000_101, 0b000_000_011,
                0b000_110_000, 0b00_101_000, 0b000_011_000
        };
        int xcount = 0;
        int ocount = 0;
        int xmask = 0;
        int omask = 0;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < 3; y++) {
                var cell = board[x].charAt(y);
                if (cell == 'X') {
                    xmask |= 1 << (x * 3 + y);
                    xcount++;
                } else if (cell == 'O') {
                    omask |= 1 << (x * 3 + y);
                    ocount++;
                }
            }
        }
        if (xcount != ocount && xcount != ocount + 1) {
            return false;
        }
        int xwinmask = 0;
        int owinmask = 0;
        for (int i = 0; i < masks.length; i++) {
            if ((xmask & masks[i]) == masks[i]) {
                xwinmask |= 1 << i;
            }
            if ((omask & masks[i]) == masks[i]) {
                owinmask |= 1 << i;
            }
            if (xwinmask > 0 && owinmask > 0) {
                return false;
            }
        }
        for (int i = 0; i < invalidWin.length; i++) { // wrong win
            if ((xwinmask & invalidWin[i]) == invalidWin[i]) {
                return false;
            }
            if ((owinmask & invalidWin[i]) == invalidWin[i]) {
                return false;
            }
        }

        if (xwinmask >= 1) {
            return xcount == ocount + 1;
        }
        if (owinmask >= 1) {
            return xcount == ocount;
        }
        return true;
    }
}
