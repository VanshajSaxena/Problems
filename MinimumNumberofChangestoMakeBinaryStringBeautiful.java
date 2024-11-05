// https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/description/

/**
 * **Minimum Number of Changes to Make Binary String Beautiful**:
 *
 * You are given a **0-indexed** binary string `s` having an even length.
 *
 * A string is **beautiful** if it&#39;s possible to partition it into one or
 * more
 * substrings such that:
 *
 *
 * - Each substring has an **even length**.
 * - Each substring contains **only** `1`'s or **only** `0`'s.
 *
 *
 * You can change any character in `s` to `0` or `1`.
 *
 * Return _the **minimum** number of changes required to make the string `s`
 * beautiful_.
 */

public class MinimumNumberofChangestoMakeBinaryStringBeautiful {
  public int minChanges(String s) {

    char[] strCharArr = s.toCharArray();
    int minChange1 = 0;
    int minChange2 = 0;

    for (int i = 1; i < strCharArr.length; i += 2) {
      if (strCharArr[i - 1] != strCharArr[i]) {
        minChange1++;
      }
      if (strCharArr[i - 1] == strCharArr[i]) {
        minChange2++;
      }
    }
    return Math.min(minChange1, minChange2);
  }
}
