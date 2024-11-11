package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may
 * exist one celebrity. The definition of a celebrity is that all the other n - 1 people know
 * him/her but he/she does not know any of them.
 *
 * <p>Now you want to find out who the celebrity is or verify that there is not one. The only thing
 * you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of
 * whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as
 * few questions as possible (in the asymptotic sense).
 *
 * <p>You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement
 * a function int findCelebrity(n), your function should minimize the number of calls to knows.
 *
 * <p>Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's
 * label if there is a celebrity in the party. If there is no celebrity, return -1.
 */
public class FindTheCelebrity {

    public static void main(String[] args) {
        System.out.println(new FindTheCelebrity().findCelebrity(7));
    }

    private final int[][] ansMap;
    public  FindTheCelebrity() {
        ansMap = new int[][] {
                /*        0  1  2  3  4  5  6 */
                /* 0: */ {0, 1, 1, 1, 1, 1, 1},
                /* 1: */ {1, 0, 1, 1, 0, 0, 0},
                /* 2: */ {0, 1, 0, 1, 1, 0, 0},
                /* 3: */ {0, 0, 0, 0, 0, 0, 0},
                /* 4: */ {0, 0, 1, 1, 0, 1, 0},
                /* 5: */ {0, 0, 0, 1, 1, 0, 1},
                /* 6: */ {1, 0, 0, 1, 0, 1, 0}
        };
    }

    public int findCelebrity(int n) {
        if (n <= 1) {
            return -1;  // Not valid
        }
        var asker = 0;
        var answer = 1;
        // find candidate
        //      (asker, answer): yes => asker = next_num, no => answer = next_num
        for (int idx=2; idx < n; idx++) {
            if (knows(asker, answer)) {
                asker = idx;
            } else {
                answer = idx;
            }
        }
        int candidate = knows(asker, answer) ? answer : asker;
        // validate candidate
        for (int idx=0; idx<n; idx++) {
            if (idx == candidate) {
                continue;
            }
            if (! knows(idx, candidate)) {
                return -1;
            }
        }
        return candidate;
    }

    private boolean knows(int a, int b) {
        return ansMap[a][b] == 1;
    }
}
