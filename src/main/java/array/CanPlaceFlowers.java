package array;

/**
 * You have a long flowerbed in which some of the plots are planted, and some are not. However,
 * flowers cannot be planted in adjacent plots.
 * <p>
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not
 * empty, and an integer n, return true if n new flowers can be planted in the flowerbed without
 * violating the no-adjacent-flowers rule and false otherwise.
 * <p>
 * Example 1:
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: True
 * <p>
 * Example 2:
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: False
 * <p>
 * Note: The input array won't violate no-adjacent-flowers rule.
 * The input array size is in the range of [1, 20000]. n is a non-negative integer which won't
 * exceed the input array size.
 */
public class CanPlaceFlowers {

    public static void main(String[] args) throws Exception {
        int[] n = {1, 0, 0, 0, 1};
        System.out.println(new CanPlaceFlowers().canPlaceFlowers(n, 1));
    }

    /*
    Plant flower in bed int[], Brownfield: 0 or 1 (has flower)
    input n (n new flower)
    return True if OK otherwise False

    hasflower = false;
    for (int i=0; i<flowerbed.length & n>0; i++)
        if (flowerbed[i] == 1) {
            hasflower = true;
        }
        if (hasFlower) {
            continue;
        }
        n--;
        hasFlower = true;

     return n <= 0;
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        boolean hasFlower = false;
        for (int ii = 0; ii < flowerbed.length; ii++) {
            if (hasFlower) {
                hasFlower = false;
                continue;
            }
            if (flowerbed[ii] == 1) {
                hasFlower = true;
                continue;
            }
            if (ii == flowerbed.length - 1 || flowerbed[ii + 1] == 0) {
                hasFlower = true;
                n--;
            }
        }
        return n <= 0;
    }
}
