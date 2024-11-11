package array;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by gouthamvidyapradhan on 08/05/2019 Given a binary array, find the maximum number of
 * consecutive 1s in this array if you can flip at most one 0.
 *
 * <p>Example 1: Input: [1,0,1,1,0] Output: 4 Explanation: Flip the first zero will get the the
 * maximum number of consecutive 1s. After flipping, the maximum number of consecutive 1s is 4.
 * Note:
 *
 * <p>The input array will only contain 0 and 1. The length of input array is a positive integer and
 * will not exceed 10,000 Follow up: What if the input numbers come in one by one as an infinite
 * stream? In other words, you can't store all numbers coming from the stream as it's too large to
 * hold in memory. Could you solve it efficiently?
 *
 * <p>Solution: O(N) Maintain a left and right auxiliary array with counts of contagious 1's from
 * both directions. Now, iterate through the array and flip a 0 to 1 and sum up left and right
 * contagious sum of 1's and return the max sum as the answer
 */
public class MaxConsecutiveOnesII {
    public static void main(String[] args) {
        System.out.println(new MaxConsecutiveOnesII().findMaxConsecutiveOnes(new int[]{1, 1, 0, 1,1,1}));
        System.out.println(new MaxConsecutiveOnesII().longestOnesOK(new int[]{1,0,0,  1,0,0,1,0,  1,1,1,1,0, 1,1,1,1,0, 1,1,1,1,1, 1,0,1,1,1, 0,0,1,1,1,   0,0,1,0,1,0,0,1,0,0,1,1}, 9));
    }


    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int cur = 0;
        var flip = false;
        for(var num: nums) {
            if (num == 1) {
                cur++;
            } else if (! flip) {
                cur++;
                flip = true;
            } else if (cur != 0) {
                max = Math.max(max, cur);
                cur = 0;
                flip = false;
            }
        }
        if (! flip && cur != nums.length) {
            cur++;
        }
        return Math.max(max, cur);
    }

    public int longestOnes(int[] nums, int k) {
        var max = this.longestOnesInternal(nums, k);
        for (int i=0; i < nums.length / 2; i++) {
            var num = nums[i];
            nums[i] = nums[nums.length -1 - i];
            nums[nums.length - 1 - i] = num;
        }
        var max2 = this.longestOnesInternal(nums, k);
        return Math.max(max, max2);
    }
    public int longestOnesInternal(int[] nums, int k) {
        // --> flip from begin
        // <-- flip from tail
        // 1,0,0,1,0, 0,1,0,1,1, 1,1,0,1,1, 1,1,0,1,1, 1,1,1,1,0, 1,1,1,0,0, 1,1,1,0,0, 1,0,1,0,0,1,0,0,1,1
        //              ^                                                                   ^
        int max = 0;
        int cur = 0;
        var flip = k;
        for(var num: nums) {
            if (num == 1) {
                cur++;
            } else if (cur > 0 && flip > 0) {
                cur++;
                flip--;
            } else if (cur != 0) {
                max = Math.max(max, cur);
                cur = 0;
                flip = k;
            }
        }
        if (flip != 0 && cur != nums.length) {
            cur = Math.min(cur + flip, nums.length);
        }
        return Math.max(max, cur);
    }

    public int longestOnesOK(int[] nums, int k) {
        int i=0,j=0;
        int n=nums.length,countZeroes=0,ans=Integer.MIN_VALUE;

        while(j<n) {
            if(nums[j]==0) {
                countZeroes++;
            }
            while(countZeroes>k) {
                if(nums[i]==0) {
                    countZeroes--;
                }i++;
            }
            ans = Math.max(ans,j-i+1);
            j++;
        }
        return ans;
    }
}
