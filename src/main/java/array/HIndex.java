package array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Given an array of integers citations where citations[i] is the number of citations a researcher
 * received for their ith paper, return the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value
 * of h such that the given researcher has published at least h papers that have each been cited
 * at least h times.
 *
 * Example 1:
 *
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
 * Example 2:
 *
 * Input: citations = [1,3,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 * <p>The first value at index i, from right to left in array S which has i <= Si is the answer.
 */
public class HIndex {

    public static void main(String[] args) throws Exception {
        // int[] A = {3, 0, 6, 1, 5};
        // int[] A = {100};
        // int[] A = {1,3,1};
        // System.out.println(new HIndex().hIndex(new int[]{0, 1, 1}));
        // System.out.println(new HIndex().hIndex(new int[]{3, 0, 6, 1, 5}));
        // System.out.println(new HIndex().hIndex(new int[]{100}));
        // System.out.println(new HIndex().hIndex(new int[]{1, 1}));
        System.out.println(new HIndex().hIndex(new int[]{2, 3, 2}));
    }

    public int hIndex(int[] citations) {
        // valid returns 0 - citations.length,
        var buckets = new int[citations.length + 1];    // idx: citation, value: paper
        for (int x: citations) {
            buckets[Math.min(x, buckets.length - 1)] ++;
        }
        int paperCnt = 0;
        for(int i=buckets.length-1; i>=0; i--) {
            paperCnt += buckets[i];
            if(i<=paperCnt)
                return i; // more than i paper have at least i citations
        }
        return 0;
    }

    public int hIndexOK(int[] citations) {
        // 3, 0, 6, 1, 5
        // {0, 1, 5), {1, 1, 4}, {3, 1, 3}, {5, 1, 2}, {6, 1, 1}  : {cita, paper, paper-min-cita
        //      papers-min-cita >= cita   => return paper-min-cita
        // {1, 2, 3}, {3, 1, 1}
        //      // 1
        var citationMap = new HashMap<Integer, Integer>();
        for(var citation: citations) {
            if (citationMap.containsKey(citation)) {
                citationMap.put(citation, citationMap.get(citation) + 1);
            } else {
                citationMap.put(citation, 1);
            }
        }
        var citationList = new ArrayList<int[]>();
        citationMap.entrySet().forEach(entry-> citationList.add(new int[]{entry.getKey(), entry.getValue(), 0}));
        citationList.sort((Comparator.comparingInt(o -> o[0])));
        var tmp = 0;
        // cal paper-min-cita
        for(int i=citationList.size() - 1; i >= 0; i--) {
            var elm = citationList.get(i);
            elm[2] = elm[1] + tmp;
            tmp = elm[2];
        }
        for (int i=0; i < citationList.size() - 1; i++) {
            var elm = citationList.get(i);
            if (elm[2] <= elm[0]) {
                return elm[2];
            } else {
                var elm2 = citationList.get(i+1);
                if (elm2[2] <= elm[0]) {
                    return elm[0];
                }
            }
        }
        var elm = citationList.get(citationList.size() -1);
        return Math.min(elm[0], elm[2]);
    }
}
