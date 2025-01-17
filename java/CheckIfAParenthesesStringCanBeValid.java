// https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/description/

import java.util.Stack;

/**
 * **Check If A Parentheses String Can Be Valid**:
 *
 * A parentheses string is a non-empty string consisting only of `'('` and
 * `')'`. It is valid if any of the following conditions is true:
 *
 * - It is `()`.
 * - It can be written as `AB` (`A` concatenated with `B`), where `A` and `B`
 * are valid parentheses strings.
 * - It can be written as `(A)`, where `A` is a valid parentheses string.
 *
 * You are given a parentheses string `s` and a string `locked`, both of length
 * `n`. `locked` is a binary string consisting only of `'0'`s and `'1'`s. For
 * each index `i` of `locked`,
 *
 * - If `locked[i]` is `'1'`, you cannot change `s[i]`.
 * - But if `locked[i]` is `'0'`, you can change `s[i]` to either `'('` or
 * `')'`.
 *
 * Return `true` _if you can make `s` a valid parentheses string_. Otherwise,
 * return `false`.
 */

public class CheckIfAParenthesesStringCanBeValid {

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/editorial)
   */
  public boolean canBeValidI(String s, String locked) {
    int length = s.length();

    // If length of string is odd, return false.
    if (length % 2 == 1) {
      return false;
    }

    Stack<Integer> openBrackets = new Stack<>();
    Stack<Integer> unlocked = new Stack<>();

    // Iterate through the string to handle '(' and ')'
    for (int i = 0; i < length; i++) {
      if (locked.charAt(i) == '0') {
        unlocked.push(i);
      } else if (s.charAt(i) == '(') {
        openBrackets.push(i);
      } else if (s.charAt(i) == ')') {
        if (!openBrackets.empty()) {
          openBrackets.pop();
        } else if (!unlocked.empty()) {
          unlocked.pop();
        } else {
          return false;
        }
      }
    }

    // Match remaining open brackets with unlocked characters
    while (!openBrackets.empty() &&
        !unlocked.empty() &&
        openBrackets.peek() < unlocked.peek()) {
      openBrackets.pop();
      unlocked.pop();
    }

    if (!openBrackets.empty()) {
      return false;
    }

    return true;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/editorial)
   */
  public boolean canBeValidII(String s, String locked) {
    int length = s.length();

    // If length of string is odd, return false.
    if (length % 2 == 1) {
      return false;
    }
    int openBrackets = 0;
    int unlocked = 0;

    // Iterate through the string to handle '(' and ')'.
    for (int i = 0; i < length; i++) {
      if (locked.charAt(i) == '0') {
        unlocked++;
      } else if (s.charAt(i) == '(') {
        openBrackets++;
      } else if (s.charAt(i) == ')') {
        if (openBrackets > 0) {
          openBrackets--;
        } else if (unlocked > 0) {
          unlocked--;
        } else {
          return false;
        }
      }
    }

    // Match remaining open brackets with unlocked characters.
    int balance = 0;
    for (int i = length - 1; i >= 0; i--) {
      if (locked.charAt(i) == '0') {
        balance--;
        unlocked--;
      } else if (s.charAt(i) == '(') {
        balance++;
        openBrackets--;
      } else if (s.charAt(i) == ')') {
        balance--;
      }
      if (balance > 0) {
        return false;
      }
      if (unlocked == 0 && openBrackets == 0) {
        break;
      }
    }

    if (openBrackets > 0) {
      return false;
    }

    return true;
  }
}
