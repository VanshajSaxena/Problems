// https://leetcode.com/problems/construct-k-palindrome-strings/description/

/**
 * **Construct K Palindrome Strings**:
 *
 * Given a string `s` and an integer `k`, return `true` if you can use all the
 * characters in `s` to construct `k` palindrome strings or `false` otherwise.
 */

public class ConstructKPalindromeStrings {
  public boolean canConstruct(String s, int k) {
    if (s.length() < k) {
      return false;
    }

    int[] freq = new int[26];
    for (char c : s.toCharArray()) {
      freq[c - 'a']++;
    }

    int odd = 0;
    for (int i : freq) {
      odd += i % 2 == 1 ? 1 : 0;
    }

    return odd <= k;
  }
}
