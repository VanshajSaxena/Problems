// https://leetcode.com/problems/word-break/description/

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * **Word Break**:
 *
 * Given a string `s` and a dictionary of strings `wordDict`, return `true` if
 * `s` can be segmented into a space-separated sequence of one or more
 * dictionary words.
 *
 * **Note** that the same word in the dictionary may be reused multiple times in
 * the segmentation.
 */

// *** Comments were written with AI.

public class WordBreak {

  // Method to check if string `s` can be segmented using recursion with
  // memoization
  public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> wordSet = new HashSet<>(wordDict); // Store words in a set for quick lookup
    Map<Integer, Boolean> memo = new HashMap<>(); // Memoization map to avoid redundant work
    return recurWordBreak(s, wordSet, memo, 0); // Start recursion from index 0
  }

  // Recursive helper method to check if the substring starting from `start` can
  // be segmented
  private boolean recurWordBreak(String s, Set<String> wordSet, Map<Integer, Boolean> memo, int start) {
    // Base case: if `start` reaches the end of the string, return true (whole
    // string is segmented)
    if (start == s.length()) {
      return true;
    }

    // Check if this starting index has been computed before (memoization)
    if (memo.containsKey(start)) {
      return memo.get(start);
    }

    // Try all possible substrings starting from `start` and ending at `end`
    for (int end = start + 1; end <= s.length(); end++) {
      // Extract the substring `s[start:end]`
      String substring = s.substring(start, end);
      // Check if substring is in dictionary and if the remaining string can be
      // segmented
      if (wordSet.contains(substring) && recurWordBreak(s, wordSet, memo, end)) {
        memo.put(start, true); // Memorize the result as true for this `start` index
        return true;
      }
    }

    // Memorize the result as false if no valid segmentation was found for `start`
    memo.put(start, false);
    return false;
  }

  // Method to check if string `s` can be segmented using dynamic programming
  // (iterative approach)
  public boolean iterWordBreak(String s, List<String> wordDict) {
    int length = s.length(); // Length of the input string

    // Edge case: an empty string can always be segmented
    if (length == 0) {
      return true;
    }

    Set<String> wordSet = new HashSet<>(wordDict); // Store words in a set for quick lookup
    boolean[] dp = new boolean[length + 1]; // DP array to store if substring `s[0:i]` can be segmented
    dp[0] = true; // Base case: an empty substring can always be segmented

    // Fill the DP array
    for (int i = 1; i <= length; i++) {
      // Check every possible substring `s[j:i]`
      for (int j = 0; j < i; j++) {
        // If `s[0:j]` can be segmented and `s[j:i]` is in the dictionary
        if (dp[j] && wordSet.contains(s.substring(j, i))) {
          dp[i] = true; // Set `dp[i]` to true (substring `s[0:i]` can be segmented)
          break; // Stop further checks for this `i` since we found a valid segmentation
        }
      }
    }

    // The result is stored in `dp[length]` (whether the whole string can be
    // segmented)
    return dp[length];
  }
}
