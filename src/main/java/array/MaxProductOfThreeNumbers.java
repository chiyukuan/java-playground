package array;

import java.util.Arrays;

/**
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.
 *
 * <p>Example 1: Input: [1,2,3] Output: 6 Example 2: Input: [1,2,3,4] Output: 24 Note: The length of
 * the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
 * Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 */
public class MaxProductOfThreeNumbers {
    public static void main(String[] args) {
        int[] A = {1, 2, 3};
        System.out.println(new MaxProductOfThreeNumbers().maximumProduct(A));
    }

    public int maximumProduct(int[] nums) {
        // keep only 5 nums, sorting is not required.
        var lowNums = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        var highNums = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

        for (var num: nums) {
            if (num < lowNums[1]) {
                if (num < lowNums[0]) {
                    lowNums[1] = lowNums[0];
                    lowNums[0] = num;
                } else {
                    lowNums[1] = num;
                }
            }
            if (num > highNums[2]) {
                if (num > highNums[1]) {
                    if (num > highNums[0]) {
                        highNums[2] = highNums[1];
                        highNums[1] = highNums[0];
                        highNums[0] = num;
                    } else {
                        highNums[2] = highNums[1];
                        highNums[1] = num;
                    }
                } else {
                    highNums[2] = num;
                }
            }
        }
        return Math.max(highNums[0] * highNums[1] * highNums[2], highNums[0] * lowNums[0] * lowNums[1]);
    }
}
