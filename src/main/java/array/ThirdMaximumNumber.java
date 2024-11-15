package array;

/**
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist,
 * return the maximum number. The time complexity must be in O(n).
 *
 * Example 1:
 *      Input: [3, 2, 1]
 *      Output: 1
 *      Explanation: The third maximum is 1.
 *
 * Example 2:
 *      Input: [1, 2]
 *      Output: 2
 *      Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 *
 * Example 3:
 *      Input: [2, 2, 3, 1]
 *      Output: 1
 *      Explanation: Note that the third maximum here means the third maximum distinct number. Both numbers
 *      with value 2 are both considered as second maximum.
 */
public class ThirdMaximumNumber {

    public static void main(String[] args) throws Exception {
        int[] a = {1, 2};
        System.out.println(new ThirdMaximumNumber().thirdMax(a));
    }

    public int thirdMax(int[] nums) {
        int[] max = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        int count = 0;

        for (var num : nums) {
            if (num > max[2]) {
                if (num > max[1]) {
                    if (num > max[0]) {
                        max[2] = max[1];
                        max[1] = max[0];
                        max[0] = num;
                        count++;
                    } else if (num < max[0]){
                        max[2] = max[1];
                        max[1] = num;
                        count++;
                    }
                } else if (num < max[1]){
                    max[2] = num;
                    count++;
                }
            }
        }
        return count < 3 ? max[0] : max[2];
    }

    public int thirdMax2(int[] nums) {
        long[] max = {Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE};
        int count = 0;
        for (int num : nums) {
            for (int j = 0; j < 3; j++) {
                if (max[j] > num) continue;
                else if (max[j] == num) break;
                int k = j;
                long temp1, temp2;
                temp1 = num;
                count++;
                while (k < 3) {
                    temp2 = max[k];
                    max[k] = temp1;
                    temp1 = temp2;
                    k++;
                }
                break;
            }
        }
        System.out.println(Integer.MIN_VALUE);
        return (count >= 3) ? (int) max[2] : (int) max[0];
    }
}
