package array;

/**
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of
 * the battleships on board.
 * <p>
 * Battleships can only be placed horizontally or vertically on board. In other words, they can only
 * be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any
 * size. At least one horizontal or vertical cell separates between two battleships (i.e., there are
 * no adjacent battleships).
 * <p>
 * Example 1:
 * Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
 * Output: 2
 * <p>
 * Example 2:
 * Input: board = [["."]]
 * Output: 0
 * <p>
 * Solution:
 * Could you do it in one-pass, using only O(1) extra memory and without modifying the values board?
 */
public class BattleshipsInABoard {
    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        char[][] board = {{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}};
        System.out.println(new BattleshipsInABoard().countBattleships(board));
    }

    // battleship horizontal or vertical, no connect between battleships.
    // return battleship count

    /*
     for each row, x
         for each cell, y
             if board[x][y] == 'X' {
                 if (x!=xMax) {
                    if (board[x+1][y] == 'X'; continue
                 }
                 if (y!=yMax) {
                    if (board[x][y+1] == 'X'; continue
                 }
                 count++;
              }
     */
    public int countBattleships(char[][] board) {
        int result = 0;
        for (int x=0; x<board.length; x++) {
            for (int y=0; y<board[0].length; y++) {
                if (board[x][y] != 'X') continue;
                if (x != board.length-1 && board[x+1][y] == 'X') continue;
                if (y != board[0].length-1 && board[x][y+1] == 'X') continue;
                result++;
            }
        }
        return result;
    }
}
