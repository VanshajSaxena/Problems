// https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/description/

/**
 * **Count Prefix And Suffix Pairs I**:
 *
 * You are given a **0-indexed** string array `words`.
 *
 * Let's define a **boolean** function `isPrefixAndSuffix` that takes two
 * strings, `str1` and `str2`:
 *
 * - `isPrefixAndSuffix(str1, str2)` returns `true` if `str1` is **both** a
 * prefix and a suffix of `str2`, and `false` otherwise.
 *
 * For example, `isPrefixAndSuffix("aba", "ababa")` is `true` because `"aba"` is
 * a prefix of `"ababa"` and also a suffix, but `isPrefixAndSuffix("abc",
 * "abcd")` is `false`.
 *
 * Return _an integer denoting the **number** of index pairs `(i, j)` such that
 * `i < j`, and `isPrefixAndSuffix(words[i], words[j])` is `true`_.
 */

public class CountPrefixAndSuffixPairsI {
  public int countPrefixSuffixPairs(String[] words) {
    int lenght = words.length;
    int pairs = 0;

    for (int left = 0; left < lenght; left++) {
      for (int right = left + 1; right < lenght; right++) {
        pairs += isPrefixAndSuffix(words[left], words[right]);
      }
    }
    return pairs;
  }

  private int isPrefixAndSuffix(String str1, String str2) {
    return str2.startsWith(str1) && str2.endsWith(str1) ? 1 : 0;
  }
}
