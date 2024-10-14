// https://leetcode.com/problems/rotate-string/description/

/**
 * **Rotate String**:
 *
 * Given two strings `s` and `goal`, return _`true` if and only if `s` can
 * become `goal` after some number of shifts on `s`_.
 *
 * A shift on `s` consists of moving the leftmost character of `s` to the
 * rightmost position.
 *
 * For example, if `s = "abcde"`, then it will be `"bcdea"` after one shift.
 */

public class RotateString {

  public boolean rotateString(String s, String goal) {

    // Check if string lengths do not match.
    if (s.length() != goal.length()) {
      return false;
    }

    // Concatenate `s` with itself.
    String concatenated = s + s;

    // Check if `concatenated` contains `goal`.
    if (!concatenated.contains(goal)) {
      return false;
    }
    return true;
  }
}
