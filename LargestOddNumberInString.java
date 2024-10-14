// https://leetcode.com/problems/largest-odd-number-in-string/description/

/**
 * **Largest OddNumber In String**:
 *
 * You are given a string `num`, representing a large integer. Return _the
 * **largest-valued** odd integer (as a string) that is a **non-empty**
 * **substring** of `num`, or an empty string `""` if no odd integer exists_.
 *
 * A **substring** is a contiguous sequence of characters within a string.
 */

public class LargestOddNumberInString {

  // Search from the last till the start, if found an odd number return substring
  // from `0` to that index. If not found return empty string.
  public String largestOddNumber(String num) {
    int firstOdd = -1;
    for (int i = num.length() - 1; i >= 0; i--) {
      char c = num.charAt(i);
      if (Character.getNumericValue(c) % 2 == 1) {
        firstOdd = i;
        break;
      }
    }
    return (firstOdd == -1) ? "" : num.substring(0, firstOdd + 1);
  }
}
