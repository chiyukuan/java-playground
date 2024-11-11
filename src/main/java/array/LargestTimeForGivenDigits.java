package array;

/**
 * Given an array arr of 4 digits, find the latest 24-hour time that can be made using each digit exactly once.
 *
 * 24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00 and 59. The
 * earliest 24-hour time is 00:00, and the latest is 23:59.
 *
 * Return the latest 24-hour time in "HH:MM" format. If no valid time can be made, return an empty string.
 *
 * Example 1:
 *      Input: arr = [1,2,3,4]
 *      Output: "23:41"
 *      Explanation: The valid 24-hour times are "12:34", "12:43", "13:24", "13:42", "14:23", "14:32",
 *                  "21:34", "21:43", "23:14", and "23:41". Of these times, "23:41" is the latest.
 * Example 2:
 *      Input: arr = [5,5,5,5]
 *      Output: ""
 *      Explanation: There are no valid 24-hour times as "55:55" is not valid.
 */
public class LargestTimeForGivenDigits {
    public static void main(String[] args) {
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(new int[]{2, 0, 6, 6}));
    }

    public String largestTimeFromDigits(int[] arr) {
        // 24: 02, 06, 20
        // 60: 02, 06, 20, 60, Not allow digit 6
        // Biggest pair with pair-1 less than 24 and pair-2 less than 60

        int max = -1;
        int hourMax = 0;
        int minMax = 0;
        for(int i=0; i<arr.length; i++) {
            if (arr[i] > 2) continue;
            for(int j=0; j < arr.length; j++) {
                var hours = arr[i] * 10 + arr[j];
                if (j == i || hours >= 24) continue;
                for (int k=0; k < arr.length; k++) {
                    if (k == i || k == j || k >= 6) continue;
                    int mins = arr[k] * 10 + arr[6 - i - j - k];
                    if (mins >= 60) continue;
                    var cur = hours * 100 + mins;
                    if (cur > max) {
                        max = cur;
                        hourMax = hours;
                        minMax = mins;
                    }
                }
            }
        }
        return max == -1 ? "" : String.format("%02d:%02d", hourMax, minMax);
    }
}
