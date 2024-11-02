// https://leetcode.com/problems/palindrome-partitioning/

import java.util.ArrayList;
import java.util.List;

/**
 * **Palindrome Partitioning**:
 *
 * Given a string `s`, partition `s` such that every substring of the partition
 * is a palindrome . Return _all possible palindrome partitioning of `s`_.
 */

public class PalindromePartitioning {

  public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    recurPartition(result, new ArrayList<>(), 0, s);
    return result;
  }

  private void recurPartition(List<List<String>> result, ArrayList<String> part, int idx, String s) {
    if (idx == s.length()) {
      result.add(new ArrayList<>(part));
      return;
    }

    for (int i = idx; i < s.length(); ++i) {
      if (isPalindrome(s, idx, i)) {
        part.add(s.substring(idx, i + 1));
        recurPartition(result, part, i + 1, s);
        part.remove(part.size() - 1);
      }
    }
  }

  private boolean isPalindrome(String s, int start, int end) {
    while (start <= end) {
      if (s.charAt(start++) != s.charAt(end--)) {
        return false;
      }
    }
    return true;
  }
}
