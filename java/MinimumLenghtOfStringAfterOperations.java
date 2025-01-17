// https://leetcode.com/problems/minimum-length-of-string-after-operations/description/

/**
 * **Minimum Lenght Of String After Operations**:
 *
 * You are given a string `s`.
 *
 * You can perform the following process on `s` any number of times:
 *
 * - Choose an index `i` in the string such that there is **at least** one
 * character to the left of index `i` that is equal to `s[i]`, and **at least**
 * one character to the right that is also equal to s[i].
 * - Delete the **closest** character to the **left** of index `i` that is equal
 * to `s[i]`.
 * - Delete the **closest** character to the **right** of index `i` that is
 * equal to `s[i]`.
 *
 * Return the **minimum** length of the final string `s` that you can achieve.
 */

public class MinimumLenghtOfStringAfterOperations {
  public int minimumLength(String s) {
    int length = s.length();
    int[] freq = new int[26];
    for (int i = 0; i < length; i++) {
      freq[s.charAt(i) - 'a']++;
    }

    int totalLength = 0;
    for (int occurance : freq) {
      if (occurance == 0) {
        continue;
      }
      totalLength += occurance % 2 == 0 ? 2 : 1;
    }

    return totalLength;
  }
}
