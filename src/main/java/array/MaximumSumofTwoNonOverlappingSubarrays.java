package array;

import java.util.*;

/**
 * Given an integer array nums and two integers firstLen and secondLen, return the maximum sum of
 * elements in two non-overlapping subarrays with lengths firstLen and secondLen.
 *
 * The array with length firstLen could occur before or after the array with length secondLen,
 * but they have to be non-overlapping.
 *
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 *      Input: nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
 *      Output: 20
 *      Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
 *
 * Example 2:
 *      Input: nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
 *      Output: 29
 *      Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
 *
 * Example 3:
 *      Input: nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
 *      Output: 31
 *      Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [0,3,8] with length 3.
 *
 * Constraints:
 *      1 <= firstLen, secondLen <= 1000
 *      2 <= firstLen + secondLen <= 1000
 *      firstLen + secondLen <= nums.length <= 1000
 *      0 <= nums[i] <= 1000
 */
public class MaximumSumofTwoNonOverlappingSubarrays {
    public static void main(String[] args) {
        System.out.println(new MaximumSumofTwoNonOverlappingSubarrays().maxSumTwoNoOverlapWindow(
                // new int[]{0,6,5,2,2,5,1,9,4}, 1, 2  // 20
                // new int[]{3,8,1,3,2,1,8,9,0}, 3, 2  // 29
                // new int[]{2,1,5,6,0,9,5,0,3,8}, 3, 2  // 26
                new int[]{2, 1, 5, 6, 0, 9, 5, 0, 3, 8}, 4, 3  // 31
        ));
    }

    public int[] initArray(int[] nums, int size) {
        var ret = new int[nums.length - size + 1];
        var prev = 0;
        for (int i=0; i < size - 1; i++) {
            prev += nums[i];
        }
        for (int i=0; i < ret.length; i++) {
            ret[i] = prev + nums[i + size - 1];
            prev = ret[i] - nums[i];
        }
        return ret;
    }

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int max = Integer.MIN_VALUE;
        var firstArrays = this.initArray(nums, firstLen);
        var secondArrays = this.initArray(nums, secondLen);

        for (int i=0; i<firstArrays.length; i++) {
            for (int j=0; j<secondArrays.length; j++) {
                var cur = firstArrays[i] + secondArrays[j];
                if (cur > max) {
                    if (i < j ) {
                        if (i + firstLen <= j) {
                            max = cur;
                        }
                    } else if (j < i) {
                        if (j + secondLen <= i) {
                            max = cur;
                        }
                    }
                }
            }
        }
        return max;
    }

    // Two cases (Left group, Right Group)
    //  First-Group followed by Second-Group
    //  Second-Group followed by First-Group
    //  |<-  Max Left Group always ->|[Max Right group].....
    public int maxSumTwoNoOverlapWindow(int[] nums, int firstLen, int secondLen) {
        int firstLeft=0;
        int secLeft=0;
        int firstRight=0;
        int secRight=0;

        for(int i=0;i<Math.max(firstLen,secondLen);i++){
            if(i<firstLen){
                firstLeft+=nums[i];
            }
            if(i<secondLen){
                secLeft+=nums[i];
            }
        }

        for(int i=Math.min(firstLen,secondLen);i<secondLen+firstLen;i++){
            if(i>=secondLen){
                firstRight+=nums[i];
            }
            if(i>=firstLen){
                secRight+=nums[i];
            }
        }

        int end=firstLen+secondLen;
        int res=firstRight+secLeft;
        int firstLmax=firstLeft; //max left sum of size firstlen
        int secLmax=secLeft;    //max left sum of size seclen
        //mainitain running left(firstLeft and  secLeft) sum and max lefts both separatley, since while computing we will use max lefts but to find max we need running ones

        while(end<nums.length){
            firstRight+=nums[end]-nums[end-firstLen];
            secRight+=nums[end]-nums[end-secondLen];


            firstLeft=firstLeft+nums[end-secondLen]-nums[end-secondLen-firstLen];
            firstLmax=Math.max(firstLmax,firstLeft);

            secLeft=secLeft+nums[end-firstLen]-nums[end-firstLen-secondLen];
            secLmax=Math.max(secLeft,secLmax);

            res=Math.max(res,firstLmax+secRight);
            res=Math.max(res,secLmax+firstRight);

            end++;
        }
        return res;
    }
}
