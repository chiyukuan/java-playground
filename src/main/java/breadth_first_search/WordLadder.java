package breadth_first_search;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList
 * is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words
 * in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 * <p>
 * Example 1:
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is
 * "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 * <p>
 * Example 2:
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 * <p>
 * Constraints:
 * - 1 <= beginWord.length <= 10
 * - endWord.length == beginWord.length
 * - 1 <= wordList.length <= 5000
 * - wordList[i].length == beginWord.length
 * - beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * - beginWord != endWord
 * - All the words in wordList are unique.
 */
public class WordLadder {

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        System.out.println(new WordLadder().ladderLength("hit", "cog", list));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //initially adding beginword and sequence length 1 into queue
        //adding all words in HashSet
        HashSet<String> notVisited = new HashSet<>(wordList);
        notVisited.remove(beginWord);
        int steps = 1;

        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);
        int queueSize = queue.size();
        while (! queue.isEmpty()) {
            String cur = queue.poll();
            if (cur.equals(endWord)) return steps;

            //TC:N * cur.length * 26 *  0(1){hashset}
            //SC: 0(N) hashSet

            //for every character doing a change,so traverse to all the characters
            var curArray = cur.toCharArray();
            for (int i = 0; i < cur.length(); i++) {
                var tmp = curArray[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    curArray[i] = ch;
                    String wordToTry = new String(curArray);
                    //exist in the set?
                    if (notVisited.contains(wordToTry)) {
                        notVisited.remove(wordToTry); // remove it from notVisited
                        queue.add(wordToTry);
                    }
                    curArray[i] = tmp;
                }
            }
            if (--queueSize == 0) {
                steps++;
                queueSize = queue.size();
            }
        }
        return 0;
    }
}
