// https://leetcode.com/problems/count-vowel-strings-in-ranges/description/

/**
 * **Count Vowel Strings In Ranges**:
 *
 * You are given a **0-indexed** array of strings `words` and a 2D array of
 * integers `queries`.
 *
 * Each query `queries[i] = [li, ri]` asks us to find the number of strings
 * present in the range `li` to `ri` (both **inclusive**) of `words` that start
 * and end with a vowel.
 *
 * Return _an array `ans` of size `queries.length`, where `ans[i]` is the answer
 * to the `i`th query_.
 *
 * **Note** that the vowel letters are `'a'`, `'e'`, `'i'`, `'o'`, and `'u'`.
 */

public class CountVowelStringsInRanges {
  public int[] vowelStrings(String[] words, int[][] queries) {
    int[] prefixSum = new int[words.length];

    int idx = -1;
    int count = 0;
    for (String word : words) {
      prefixSum[++idx] = hasVowelsAtStartAndEnd(word) ? ++count : count;
    }

    idx = 0;
    int[] result = new int[queries.length];
    for (int[] query : queries) {
      int left = query[0];
      int right = query[1];

      result[idx++] = prefixSum[right] - (left == 0 ? 0 : prefixSum[left - 1]);
    }
    return result;
  }

  private boolean hasVowelsAtStartAndEnd(String word) {
    char first = word.charAt(0);
    char last = word.charAt(word.length() - 1);
    return "aeiou".indexOf(first) != -1 && "aeiou".indexOf(last) != -1;
  }
}
