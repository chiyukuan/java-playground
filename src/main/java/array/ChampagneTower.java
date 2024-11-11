package array;

/**
 * We stack glasses in a pyramid, where the first row has 1 glass, the second row has 2 glasses,
 * and so on until the 100th row.  Each glass holds one cup of champagne.
 * <p>
 * Then, some champagne is poured into the first glass at the top.  When the topmost glass is full,
 * any excess liquid poured will fall equally to the glass immediately to the left and right of it.
 * When those glasses become full, any excess champagne will fall equally to the left and right of
 * those glasses, and so on.  (A glass at the bottom row has its excess champagne fall on the floor.)
 * <p>
 * For example, after one cup of champagne is poured, the top most glass is full.  After two cups of
 * champagne are poured, the two glasses on the second row are half full.  After three cups of champagne
 * are poured, those two cups become full - there are 3 full glasses total now.  After four cups of
 * champagne are poured, the third row has the middle glass half full, and the two outside glasses
 * are a quarter full, as pictured below.
 * <p>
 * Example 1:
 * Input: poured = 1, query_glass = 1, query_row = 1 Output: 0.0
 * Explanation: We poured 1 cup of champange to the top glass of the tower (which is indexed
 * as (0, 0)). There will be no excess liquid so all the glasses under the top glass will
 * remain empty.
 * <p>
 * Example 2:
 * Input: poured = 2, query_glass = 1, query_row = 1 Output: 0.5
 * Explanation: We poured 2 cups of champange to the top glass of the tower (which is indexed
 * as (0, 0)). There is one cup of excess liquid. The glass indexed as (1, 0) and the glass
 * indexed as (1, 1) will share the excess liquid equally, and each will get half cup of champange.
 *
 * <p>poured will be in the range of [0, 10 ^ 9]. query_glass and query_row will be in the range of
 * [0, 99].
 * <p>
 * Solution: Calculate for every glass and for each row at a time. Use the value from the
 * previous row to calculate the current value.
 * <p>
 * Note:
 * - Based on the champagne flow into a cup. For example:
 * - the row n, have n cups, {M1, M2, .... Mn}
 * - The row n+1: {(M1-1)/2, (M1-1)/2 + (M2-1)/2, ...., (Mn-1)/2}   value must >= 0
 */
public class ChampagneTower {
    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new ChampagneTower().champagneTower(25, 6, 1));
    }

    /* for row = 0 to queryRow
            [ x ]          [ y ]
         [x - 1]      [y - 1]    ??  <== phase 1
                                0         <== init
                    [y-1]/2    +[y-1]/2   <== phase 2
        [x-1]/2    +[x-1]/2

        return glasses[queeryGlass] > 1 ? 1 : glasses[queeryGlass]
     */
    public double champagneTower(int poured, int queryRow, int queryGlass) {
        if (poured == 0) {
            return 0;
        }
        var glasses = new double[queryRow+1];
        glasses[0] = poured;

        for (int row=1; row <= queryRow; row++) {
            for(int idx=0; idx<=row; idx++) { // phase 1, total remaining from previous layer
                glasses[idx] = Math.max(glasses[idx] - 1, 0);
            }
            for(int idx=row-1; idx>=0; idx--) { // distribute, with idx+1, idx
                glasses[idx] = glasses[idx] / 2;
                glasses[idx+1] += glasses[idx];     // since from right to left, the idx+1 may have some value.
            }
        }
        return glasses[queryGlass] >= 1 ? 1 : glasses[queryGlass];
    }
}
