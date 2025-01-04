// https://leetcode.com/problems/substring-matching-pattern/description/

/**
 * **Substring Matching Pattern**:
 *
 * You are given a string `s` and a pattern string `p`, where `p` contains
 * **exactly one** `'*'` character.
 *
 * The `'*'` in `p` can be replaced with any sequence of zero or more
 * characters.
 *
 * Return `true` if `p` can be made a substring of `s`, and `false` otherwise.
 *
 * A **substring** is a contiguous **non-empty** sequence of characters within a
 * string.
 */

public class SubstringMatchingPattern {
  public boolean hasMatch(String s, String p) {
    int index = p.indexOf("*");

    // index of left substring.
    int left = s.indexOf(p.substring(0, index));
    // index of right substring that starts after `left + index`.
    int right = s.indexOf(p.substring(index + 1), left + index);

    if (left != -1 && right != -1) {
      return true;
    }
    return false;
  }
}
