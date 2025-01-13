// https://leetcode.com/problems/unique-length-3-palindromic-subsequences/description/

import java.util.Arrays;
import java.util.HashSet;

/**
 * **Unique Length Three Palindromic Subsequences**:
 *
 * Given a string `s`, return the number of **unique palindromes of length
 * three** that are a **subsequence of** `s`.
 *
 * Note that even if there are multiple ways to obtain the same subsequence, it
 * is still only counted once.
 *
 * A **palindrome** is a string that reads the same forwards and backwards.
 *
 * A **subsequence** of a string is a new string generated from the original
 * string with some characters (can be none) deleted without changing the
 * relative order of the remaining characters.
 *
 * - For example, `"ace"` is a subsequence of `"abcde"`.
 */

public class UniqueLengthThreePalindromicSubsequences {
  public int countPalindromicSubsequenceI(String s) {
    HashSet<Character> letters = new HashSet<>();

    for (Character letter : s.toCharArray()) {
      letters.add(letter);
    }

    int result = 0;
    for (Character letter : letters) {
      int first = -1;
      int last = -1;
      for (int k = 0; k < s.length(); k++) {
        if (s.charAt(k) == letter) {
          if (first == -1) {
            first = k;
          }
          last = k;
        }
      }

      HashSet<Character> between = new HashSet<>();
      for (int i = first + 1; i < last; i++) {
        between.add(s.charAt(i));
      }

      result += between.size();
    }

    return result;
  }

  public int countPalindromicSubsequenceII(String s) {
    int[] first = new int[26];
    int[] last = new int[26];
    Arrays.fill(first, -1);

    for (int i = 0; i < s.length(); i++) {
      int c = s.charAt(i) - 'a';
      if (first[c] == -1) {
        first[c] = i;
      }
      last[c] = i;
    }

    int result = 0;
    for (int i = 0; i < 26; i++) {
      if (first[i] == -1) {
        continue;
      }

      HashSet<Character> between = new HashSet<>();

      for (int j = first[i] + 1; j < last[i]; j++) {
        between.add(s.charAt(j));
      }

      result += between.size();
    }

    return result;
  }
}
