package array;

import java.util.Arrays;

/**
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 *
 * Return the maximum valued number you can get.
 */
public class MaximumSwap {

    public static void main(String[] args) throws Exception {
        System.out.println(new MaximumSwap().maximumSwap(983687));
    }

    public int maximumSwap(int num) {
        // swapping the biggest digit, tail is better, after current index.
        //
        // create digit latest appear index table, size 10
        // for each digit, high to low, swap it??
        var array = String.valueOf(num).toCharArray();
        var lastIdxMap = new int[10];

        for (int idx=0; idx < array.length; idx++) {
            lastIdxMap[array[idx] - '0'] = idx;
        }
        for (int idx=0; idx < array.length; idx++) {
            var digit = array[idx] - '0';
            for (int ii=lastIdxMap.length - 1; ii > digit; ii--) {
                if (lastIdxMap[ii] > idx) {
                    var tmp = array[idx];
                    array[idx] = array[lastIdxMap[ii]];
                    array[lastIdxMap[ii]] = tmp;
                    return Integer.parseInt(String.valueOf(array));
                }
            }
        }
        return num;
    }
}
