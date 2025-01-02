// https://leetcode.com/problems/word-ladder/description/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * **Word Ladder**:
 *
 * A **transformation sequence** from word `beginWord` to word `endWord` using a
 * dictionary `wordList` is a sequence of words `beginWord -> s1 -> s2 -> ... ->
 * sk` such that:
 *
 * - Every adjacent pair of words differs by a single letter.
 * - Every `si` for `1 <= i <= k` is in `wordList`. Note that `beginWord` does
 * not need to be in `wordList`.
 * - `sk == endWord`
 *
 * Given two words, `beginWord` and `endWord`, and a dictionary `wordList`,
 * return _all the **shortest transformation sequences** from `beginWord` to
 * `endWord`, or an empty list if no such sequence exists. Each sequence should
 * be returned as a list of the words `[beginWord, s1, s2, ..., sk]`_.
 */
// MARK: Pending.
public class WordLadderII {
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    Set<String> set = new HashSet<>(wordList);
    Set<String> visited = new HashSet<>();

    Queue<String> queue = new LinkedList<>();
    queue.add(beginWord);
    visited.add(beginWord);

    int shortestPathLen = 1;
    boolean found = false;
    while (!queue.isEmpty() && !found) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        String word = queue.poll();

        if (word.equals(endWord)) {
          found = true;
          break;
        }

        for (int j = 0; j < word.length(); j++) {
          for (char c = 'a'; c < 'z'; c++) {
            char[] charArr = word.toCharArray();
            charArr[j] = c;

            String str = new String(charArr);

            if (set.contains(str) && !visited.contains(str)) {
              queue.add(str);
              visited.add(str);
            }
          }
        }
      }
      if (found) {
        break;
      }
      shortestPathLen++;
    }

    List<List<String>> result = new ArrayList<>();
    Stack<String> stack = new Stack<>();
    stack.add(beginWord);
    int depth = 0;

    visited.clear();
    while (!stack.isEmpty() && depth <= shortestPathLen) {
      List<String> sequence = new ArrayList<>();
      String word = stack.pop();

      if (word.equals(endWord)) {
        result.add(new ArrayList<>(sequence));
      }
      for (int i = 0; i < word.length(); i++) {
        for (char c = 'a'; c <= 'z'; c++) {
          char[] charArr = word.toCharArray();
          charArr[i] = c;

          String str = new String(charArr);
          if (set.contains(str) && !visited.contains(str)) {
            stack.push(str);
            visited.add(str);
          }
        }
      }
      depth++;
    }
  }
}
