// https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments/description/

/**
 * **Make String A Subsequence Using Cylic Increments**:
 *
 * You are given two **0-indexed** strings `str1` and `str2`.
 *
 * In an operation, you select a set of indices in `str1`, and for each index i
 * in the set, increment `str1[i]` to the next character **cyclically**. That is
 * `'a'` becomes `'b'`, `'b'` becomes `'c'`, and so on, and `'z'` becomes `'a'`.
 *
 * Return `true`_if it is possible to make `str2` a subsequence of `str1` by
 * performing the operation **at most once**, and `false` otherwise_.
 *
 * **Note**: A subsequence of a string is a new string that is formed from the
 * original string by deleting some (possibly none) of the characters without
 * disturbing the relative positions of the remaining characters.*
 */

public class MakeStringASubsequenceUsingCylicIncrements {

  public boolean canMakeSubsequenceII(String str1, String str2) {
    int j = 0, n = str1.length(), m = str2.length();
    for (int i = 0; i < n && j < m; ++i)
      if ((str2.charAt(j) - str1.charAt(i) + 26) % 26 <= 1)
        ++j;
    return j == m;
  }

  /**
   * [LeetCode
   * Solution](https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments/solutions/3932451/java-c-python-two-pointers-checking-subsequence)
   */
  public boolean canMakeSubsequenceI(String str1, String str2) {
    int j = 0, n = str1.length(), m = str2.length();
    for (int i = 0; i < n && j < m; ++i) {
      int a = str1.charAt(i), b = str2.charAt(j);
      if (a == b || a + 1 == b || a - 25 == b)
        ++j;
    }
    return j == m;
  }
}
