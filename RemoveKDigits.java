// https://leetcode.com/problems/remove-k-digits/description/

import java.util.Stack;

/**
 * **Remove K Digits**:
 *
 * Given string num representing a non-negative integer `num`, and an integer
 * `k`, return _the smallest possible integer after removing `k` digits from
 * `num`_.
 */

public class RemoveKDigits {
  public String removeKdigits(String num, int k) {

    if (k == num.length()) {
      return "0";
    }

    Stack<Character> stack = new Stack<>();

    for (Character chr : num.toCharArray()) {
      while (!stack.isEmpty() && k > 0 && stack.peek() > chr) {
        stack.pop();
        k--;
      }

      stack.push(chr);
    }

    while (!stack.isEmpty() && k > 0) {
      stack.pop();
      k--;
    }

    StringBuilder result = new StringBuilder();

    while (!stack.isEmpty()) {
      result.append(stack.pop());
    }

    result.reverse();

    while (result.length() > 1 && result.charAt(0) == '0') {
      result.deleteCharAt(0);
    }

    return result.toString();
  }
}
