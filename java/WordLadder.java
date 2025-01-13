// https://leetcode.com/problems/word-ladder/description/

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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
 * return _the **number of words** in the **shortest transformation sequence**
 * from `beginWord` to `endWord`, or 0 if no such sequence exists_.
 */

public class WordLadder {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    // Set populated with `wordList`.
    Set<String> set = new HashSet<>(wordList);
    // Empty set to keep track of visited words.
    Set<String> visited = new HashSet<>();

    // If set does not contain `endWord`, return `0` as no path can be found.
    if (!set.contains(endWord)) {
      return 0;
    }

    Queue<String> queue = new LinkedList<>();

    queue.add(beginWord);

    // BFS travel
    int changes = 1;
    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        String word = queue.poll();
        // Return changes, if `word` equals `endWord`.
        if (word.equals(endWord)) {
          return changes;
        }
        // For each index of the `word`.
        for (int j = 0; j < word.length(); j++) {
          // For each small english letter.
          for (char c = 'a'; c <= 'z'; c++) {
            char[] charArr = word.toCharArray();
            charArr[j] = c;

            // Allocate a new string.
            String str = new String(charArr);
            // Check if it is contained in `set`, and does not contain in `visited` set,
            // only then we can visit.
            if (set.contains(str) && !visited.contains(str)) {
              // Add to queue.
              queue.add(str);
              // Mark visited.
              visited.add(str);
            }
          }
        }
      }
      changes++;
    }
    return 0;
  }
}
